package models.dal

import java.sql.Timestamp

import common.enums.PostStatus
import common.enums.PostStatus.PostStatus
import models.Post
import slick.jdbc.MySQLProfile.api._

class PostTableDef(tag: Tag) extends Table[Post](tag, "post"){
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("title")
  def content = column[String]("content")
  def authorId = column[Long]("author_id")
  def publishTime = column[Timestamp]("publish_time")
  def updateTime = column[Timestamp]("update_time")
  def categoryId = column[Long]("category_id")
  def status = column[Int]("status")
  def titleImageId = column[Long]("title_image_id")

  override def * =
    (
      id,
      title,
      content,
      authorId,
      publishTime,
      updateTime,
      categoryId,
      status,
      titleImageId
    ) <> (
      {
        case (id, title, content, authorId,
              publishTime, updateTime, categoryId,
              status, titleImageId) => Post(
              id, title, content, authorId,
              publishTime, updateTime, PostStatus(status),
              categoryId, titleImageId)
      },
      {
        p: Post => Some((p.id, p.title, p.content, p.authorId,
                        p.publishTime, p.updateTime,
                        p.categoryId, p.status.id, p.titleImageId))
      }
    )
}
