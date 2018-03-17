package models

import java.sql.Timestamp

import common.enums.PostStatus.PostStatus
import controllers.routes
import play.api.libs.json.{Json, Writes}

case class PostDetail (
                        id: Long,
                        title: String,
                        summary: String,
                        tags: List[String],
                        content: String,
                        authorId: Long,
                        authorName: String,
                        publishTime: Timestamp,
                        updateTime: Timestamp,
                        status: PostStatus,
                        categoryId: Long,
                        categoryName: String,
                        titleImageId: Long,
                        titleImageSrc: String
                      )

object PostDetail {
  implicit val postWrites = new Writes[PostDetail]{
    def writes(postDetail: PostDetail) = Json.obj(
      "id" -> postDetail.id,
      "title" -> postDetail.title,
      "summary" -> postDetail.summary,
      "tags" -> Json.arr(postDetail.tags),
      "content" -> postDetail.content,
      "authorId" -> postDetail.authorId,
      "authorName" -> postDetail.authorName,
      "publishTime" -> postDetail.publishTime.toString,
      "updateTime" -> postDetail.updateTime.toString,
      "status" -> postDetail.status.toString,
      "categoryId" -> postDetail.categoryId,
      "categoryName" -> postDetail.categoryName,
      "titleImageId" -> postDetail.titleImageId,
      "titleImageSrc" -> postDetail.titleImageSrc
    )
  }
}