package models

import controllers.routes
import play.api.libs.json.{Json, Writes}

import scala.concurrent.ExecutionContext.Implicits.global

case class Post(
             id: Long,
             title: String,
             content: String,
             authorId: Int,
             publishTime: String,
             updateTime: String,
             isPublish: Boolean,
             isReviewed: Boolean,
             categoryId: Int)

object Post {
  implicit val postWrites = new Writes[Post]{
    def writes(post: Post) = Json.obj(
      "id" -> post.id,
      "title" -> post.title,
      "content" -> post.content,
      "time" -> post.publishTime,
      "tags" -> Json.arr("旅游", "俄罗斯"),
      "comments" -> 2,
      "mark" -> "旅游",
      "summary" -> "一路向北一路向北一路向北一路向北一路向北，一路向北一路向北一路向北，一路向北从最",
      "imageSrc" -> routes.Assets.versioned("images/nav_blog.jpg").toString()

    )
  }
}



