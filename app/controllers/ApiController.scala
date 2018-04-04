package controllers

import javax.inject.{Inject, Singleton}

import models.PostDetail
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import services.PostService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class ApiController @Inject()(cc: ControllerComponents,
                              postService: PostService
                             ) extends AbstractController(cc) {

  val numOfPostPerPage: Int = 1

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def homePagePosts() = Action.async { implicit request: Request[AnyContent] =>
    val categoryId = request.getQueryString("cid").getOrElse("0").toInt
    val page = request.getQueryString("p").getOrElse("0").toInt
    val postsToJson = (posts: Seq[PostDetail]) => {
      val data = Json.obj(
        "posts" -> posts
      )
      Ok(Json.toJson(data))
    }
    categoryId match {
      case 6 => postService.getPosts(page, numOfPostPerPage).map(postsToJson)
      case 5 => postService.getPosts(page, numOfPostPerPage).map(postsToJson)
      case categoryId: Int => postService.getPostsByCategory(categoryId, page, numOfPostPerPage).map(postsToJson)
    }
  }

  def post(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    postService.getPostDetail(id).map { postDetail =>
      if (postDetail.isDefined) {
        Ok(Json.toJson(postDetail.get))
      } else {
        Ok(Json.toJson(""))
      }
    }
  }

}
