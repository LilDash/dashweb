package models.dal

import java.sql.Timestamp

import models.Post
import slick.jdbc.MySQLProfile.api._

class PostTableDef(tag: Tag) extends Table[Post](tag, "post"){
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def title = column[String]("title")
  def content = column[String]("content")
  def authorId = column[Int]("author_id")
  def publishTime = column[Timestamp]("publish_time")
  def updateTime = column[Timestamp]("update_time")
  def isPublished = column[Boolean]("is_published")
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
      isPublished,
      isReviewed,
      categoryId
    ) <> ((Post.apply _).tupled, Post.unapply)
}

