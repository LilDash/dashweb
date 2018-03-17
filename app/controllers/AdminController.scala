package controllers


import java.lang.NumberFormatException
import java.nio.file.Paths
import java.sql.Timestamp
import java.util.Date
import javax.inject._

import akka.http.scaladsl.model.DateTime
import com.google.common.html.HtmlEscapers
import common.enums.PostStatus
import common.utility.{StringUtil, TimeUtil}
import models.Post
import org.joda.time
import org.joda.time.{DateTime, Seconds}
import play.Play
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import play.api.{Configuration, Logger}




class AdminController @Inject()(cc: ControllerComponents,
                                config: Configuration,
                                editorService: EditorService,
                                imageService: ImageService,
                                categoryService: CategoryService,
                                postService: PostService)
  extends AbstractController(cc) {

  def getCurrentUser() = Action { implicit request: Request[AnyContent] =>
    val data = Json.obj(
      "success" -> true,
      "message" -> "",
      "data" -> "Dash"
    )
    Ok(Json.toJson(data))
  }

  def login() = Action { implicit request: Request[AnyContent] =>



    val password = request.body.asFormUrlEncoded.get("password").head
    val isPass = password.equals("dashwebadmin2018")

    val data = Json.obj(
      "success" -> isPass,
      "message" -> "",
      "data" -> "Dash"
    )

    Ok(Json.toJson(data))

  }

  def selectPosts() = Action.async { implicit request: Request[AnyContent] =>
    postService.listAllPosts map { posts =>
      val data = Json.obj(
        "success" -> true,
        "message" -> "",
        "total" -> 1,
        "data" -> posts
      )
      Ok(Json.toJson(data))
    }
  }

  def insertPost() = Action.async { implicit request: Request[AnyContent] =>

    var message = ""
    val post = request.body.asJson.map { json =>
      val title = xml.Utility.escape((json \ "title").as[String])
      val content = xml.Utility.escape(StringUtil.sanitizeUploadedText((json \ "content").as[String]))
      val authorId = 1
      val isPublished = (json \ "isPublished").as[String].equals("yes")
      val isReviewed = (json \ "isReviewed").as[String].equals("yes")
      val status = if (isPublished && isReviewed) PostStatus.active else PostStatus.inactive
      val titleImageId = (json \ "titleImageId").as[Long]
      val categoryId = (json \ "categoryId").as[Long]

      val date = new Date()
      val time = new Timestamp(date.getTime())
      Post(0, title, content, authorId, time, time, status, categoryId, titleImageId)
    }

    if(post.isDefined){
      postService.savePost(post.get).map { result =>
        val resultJson = Json.obj(
          "success" -> true,
          "message" -> message,
          "data" -> 1
        )
        Ok(Json.toJson(resultJson))
      }
    }else{
      val result = Json.obj(
        "success" -> false,
        "message" -> message,
        "data" -> ""
      )
      Future.successful(Ok(Json.toJson(result)))
    }
  }

  def updatePost() = Action.async { implicit request: Request[AnyContent] =>

    var message = ""
    val post = request.body.asJson.map { json =>
      val id = (json \ "id").as[Long]
      val title = xml.Utility.escape((json \ "title").as[String])
      val content = xml.Utility.escape(StringUtil.sanitizeUploadedText((json \ "content").as[String]))
      val authorId = 1
      val isPublished = (json \ "isPublished").as[String].toInt > 0
      val isReviewed = (json \ "isReviewed").as[String].toInt > 0
      val status = if (isPublished && isReviewed) PostStatus.active else PostStatus.inactive
      val titleImageId = (json \ "titleImageId").as[Long]
      val categoryId = (json \ "category").as[Long]

      val time = TimeUtil.timeStampNow()
      Post(id, title, content, authorId, time, time, status, categoryId, titleImageId)
    }

    if(post.isDefined){
      postService.savePost(post.get).map { result =>
        val resultJson = Json.obj(
          "success" -> true,
          "message" -> message,
          "data" -> result
        )
        Ok(Json.toJson(resultJson))
      }
    }else{
      val result = Json.obj(
        "success" -> false,
        "message" -> message,
        "data" -> ""
      )
      Future.successful(Ok(Json.toJson(result)))
    }

  }

  def deletePost() = Action.async { implicit request: Request[AnyContent] =>
      var effectRow = 0
      var message = ""
      try{
        val keys: Option[String] = request.getQueryString("keys");
        if(keys.isDefined){
          val postIds = keys.get.split(",").map(_.toInt)
          effectRow = postIds.fold(0) { (acc, postId) =>

            val futureRet = postService.deletePost(postId).map { result =>
              result
            }
            acc + Await.result(futureRet, 2 seconds)
          }
        }
        else{
          message = "Empty parameter: keys";
        }
      }catch{
        case ex: Exception => message = ex.getMessage()
      }

      val result = Json.obj(
        "success" -> (effectRow > 0),
        "message" -> message,
        "data" -> effectRow
      )
      Future.successful(Ok(Json.toJson(result)))
  }

  def uploadFile = Action(parse.multipartFormData) { implicit request =>
    request.body.file("file").map { picture =>

      // only get the last part of the filename
      // otherwise someone can send a path like ../../home/foo/bar.txt to write to other files on the system
      val filename = Paths.get(picture.filename).getFileName

      val path = play.Play.application().path().getAbsolutePath()

      val dest = Paths.get(s"$path/public/data/files/img/$filename")

      picture.ref.moveTo(dest, replace = true)

      val response = Json.obj(
        "success" -> true,
        "name" -> s"$filename",
        //"url" -> routes.Assets.versioned(s"data/files/img/$filename").url
      )
      Ok(Json.toJson(response))

    }.getOrElse {
      Ok("File upload failed")
    }
  }

  def getUploadFileToken = Action { implicit request =>

    import com.qiniu.util.Auth
    val auth = Auth.create("_gEUAXvk6ovxA4QXeY73odJyHjYAu92wW7tOmgaf", "_hG4LXRPck_lkVFbgsFv5SVaYdgVINDrs3vaQibt")
    val token = auth.uploadToken("dplanet")

    val response = Json.obj(
      "uptoken" -> token
    )
    Ok(Json.toJson(response))
  }

  def editor = Action.async { implicit request =>
    val action = request.getQueryString("action")
    val callback = request.getQueryString("callback")
    if (action.isDefined){

      action.get match {
        case "config" => {
          val res = editorService.getEditorConfig()
          Future.successful(responseWithContentType(request, res))
        }
        case "image" => editorService.uploadImage(request).map { res =>
          responseWithContentType(request, res)
        }
        case _ => Future.successful(NotFound("unknown action"))
      }

    } else {
      Future.successful(NotFound("unknown action"))
    }
  }

  def image = Action.async { implicit request =>
    request.method match {
      // Upload image
      case "POST" =>
        imageService.uploadImage(request, "file").map { uploadResp: UploadImageResponse =>
          val ret = if (uploadResp.isSuccess) {
            val item = Json.obj(
              "id" -> uploadResp.id,
              "name" -> uploadResp.fileName,
              "url" -> uploadResp.url,
              "thumbnail_url" -> imageService.getPreviewUrl(uploadResp.url),
              "size" -> 0,
              "type" -> "",
              "delete_url" -> s"http://${request.host}${request.uri}",
              "delete_type" -> "DELETE",
            )
            Json.arr(item)
          } else {
            Logger.error(uploadResp.message)
            Json.arr()
          }
          Ok(Json.toJson(ret))

        }
       // Remove image
      case "DELETE" =>
        val name = request.getQueryString("name")
        if (name.isDefined){
          imageService.deleteImage(name.get).map { res =>
            val ret = Json.arr(Json.obj("isSuccess" -> res))
            Ok(Json.toJson(ret))
          }
        } else {
          val ret = Json.arr(Json.obj("isSuccess" -> false))
          Future.successful(Ok(Json.toJson(ret)))
        }
      case _ =>
        Future.successful(NotFound("unknown method"));
    }
  }

  def getAllCategories = Action.async { implicit request =>
    categoryService.listAllCategories.map { categories =>
      var arr = Json.arr()
      categories.foreach { c =>
        arr = arr.append(Json.obj(
          "id" -> c.id,
          "name" -> c.name
        ))
      }
      val data = Json.obj(
        "categories" -> arr
      )
      Ok(Json.toJson(data))
    }
  }

  def responseWithContentType(request: Request[AnyContent], data: JsValue): Result ={
    val callback = request.getQueryString("callback")
    if (callback.isDefined && !callback.get.isEmpty) {
      val response = s"${callback.get}($data)"
      Ok(response).as("application/javascript; charset=utf-8")
    }else{
      Ok(data).as("application/json; charset=utf-8")
    }
  }
}
