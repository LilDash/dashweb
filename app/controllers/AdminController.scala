package controllers


import java.lang.NumberFormatException
import java.nio.file.Paths
import java.sql.Timestamp
import java.util.Date
import javax.inject._

import akka.http.scaladsl.model.DateTime
import com.google.common.html.HtmlEscapers
import common.utility.StringUtil
import models.Post
import org.joda.time
import org.joda.time.{DateTime, Seconds}
import play.Play
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.PostService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}



class AdminController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getCurrentUser() = Action { implicit request: Request[AnyContent] =>
    val data = Json.obj(
      "success" -> true,
      "message" -> "",
      "data" -> "dash"
    )
    Ok(Json.toJson(data))
  }

  def login() = Action { implicit request: Request[AnyContent] =>
    val data = Json.obj(
      "success" -> true,
      "message" -> "",
      "data" -> "dash"
    )
    Ok(Json.toJson(data))
  }

  def selectPosts() = Action.async { implicit request: Request[AnyContent] =>
    PostService.listAllPosts map { posts =>
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
      val title = (json \ "title").as[String]
      val content = StringUtil.sanitizeUploadedText((json \ "content").as[String])
      val authorId = 1
      val isPublished = (json \ "isPublished").as[String].toInt > 0
      val isReviewed = (json \ "isReviewed").as[String].toInt > 0
      val categoryId = 1

      val date = new Date()
      val time = new Timestamp(date.getTime())
      Post(0, title, content, authorId, time, time, isPublished, isReviewed, categoryId)
    }

    if(post.isDefined){
      PostService.savePost(post.get).map { result =>
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
      val title = (json \ "title").as[String]
      val content = StringUtil.sanitizeUploadedText((json \ "content").as[String])
      val authorId = 1
      val isPublished = (json \ "isPublished").as[String].toInt > 0
      val isReviewed = (json \ "isReviewed").as[String].toInt > 0
      val categoryId = 1

      val date = new Date()
      val time = new Timestamp(date.getTime())
      Post(id, title, content, authorId, time, time, isPublished, isReviewed, categoryId)
    }

    if(post.isDefined){
      PostService.savePost(post.get).map { result =>
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

            val futureRet = PostService.deletePost(postId).map { result =>
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

//  def uploadFile = Action(parse.multipartFormData) { implicit request =>
//    request.body.file("file").map { picture =>
//
//      // only get the last part of the filename
//      // otherwise someone can send a path like ../../home/foo/bar.txt to write to other files on the system
//      val filename = Paths.get(picture.filename).getFileName
//
//      val path = play.Play.application().path().getAbsolutePath()
//
//      val dest = Paths.get(s"$path/public/data/files/img/$filename")
//
//      picture.ref.moveTo(dest, replace = true)
//
//      val response = Json.obj(
//        "success" -> true,
//        "name" -> s"$filename",
//        //"url" -> routes.Assets.versioned(s"data/files/img/$filename").url
//      )
//      Ok(Json.toJson(response))
//
//    }.getOrElse {
//      Ok("File upload failed")
//    }
//  }

  def getUploadFileToken = Action { implicit request =>

    import com.qiniu.util.Auth
    val auth = Auth.create("_gEUAXvk6ovxA4QXeY73odJyHjYAu92wW7tOmgaf", "_hG4LXRPck_lkVFbgsFv5SVaYdgVINDrs3vaQibt")
    val token = auth.uploadToken("dplanet")

    val response = Json.obj(
      "uptoken" -> token
    )
    Ok(Json.toJson(response))
  }

}
