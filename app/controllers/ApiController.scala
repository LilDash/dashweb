package controllers

import javax.inject.{Inject, Singleton}

import common.enums.{LanguageEnum, PageTypeEnum}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import viewModels.HomePageViewModel
import services.PostService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.Post

@Singleton
class ApiController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def homePagePosts() = Action.async { implicit request: Request[AnyContent] =>

    PostService.getNLatestPosts(10).map { posts =>
      val data = Json.obj(
        "posts" -> posts
      )
      Ok(Json.toJson(data))
    }
  }

}
