package models.dal

import models.Post
import slick.jdbc.MySQLProfile.api._

class PostTableDef(tag: Tag) extends Table[Post](tag, "post"){
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("title")
  def content = column[String]("content")
  def authorId = column[Int]("author_id")
  def publishTime = column[String]("publish_time")
  def updateTime = column[String]("update_time")
  def isPublish = column[Boolean]("is_published")
  def isReviewed = column[Boolean]("is_reviewed")
  def categoryId = column[Int]("category_id")

  override def * =
    (
      id,
      title,
      content,
      authorId,
      publishTime,
      updateTime,
      isPublish,
      isReviewed,
      categoryId
    ) <> ((Post.apply _).tupled, Post.unapply)
}

