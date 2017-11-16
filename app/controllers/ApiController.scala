package controllers

import javax.inject.{Inject, Singleton}

import common.enums.{LanguageEnum, PageTypeEnum}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import viewModels.HomePageViewModel

@Singleton
class ApiController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def homePagePosts() = Action { implicit request: Request[AnyContent] =>
    val json: JsValue = Json.obj(
      "posts" ->  Json.arr(
        Json.obj(
          "id" -> 1,
          "imageSrc" -> routes.Assets.versioned("images/nav_blog.jpg").toString(),
          "mark" -> "旅游",
          "title" -> "从最热的曼谷，到最冷的北极圈",
          "summary" -> "一路向北一路向北一路向北一路向北一路向北，一路向北一路向北一路向北，一路向北从最热的曼谷，到最冷的北极圈从最热的曼谷，到最冷的北极圈从最热的曼谷，到最冷的北极圈从最热的曼谷，到最冷的北极圈从最热的曼谷，到最冷的北极圈",
          "time" -> "一天前",
          "tags" -> Json.arr("旅游", "俄罗斯"),
          "comments"-> 2
        ),
        Json.obj(
          "id" -> 2,
          "imageSrc" -> routes.Assets.versioned("images/nav_blog.jpg").toString(),
          "mark" -> "旅游",
          "title" -> "从最热的曼谷，到最冷的北极圈",
          "summary" -> "一路向北一路向北一路向北一路向北一路向北，一路向北一路向北一路向北，一路向北从最热的曼谷，到最冷的北极圈从最热的曼谷，到最冷的北极圈从最热的曼谷，到最冷的北极圈从最热的曼谷，到最冷的北极圈从最热的曼谷，到最冷的北极圈",
          "time" -> "一天前",
          "tags" -> Json.arr("旅游", "俄罗斯"),
          "comments"-> 3
        )
      )
    );
    Ok(json);
  }
}
