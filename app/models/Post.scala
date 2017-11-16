package models

import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._
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
    ) <> (Post.tupled, Post.unapply)
}

object Posts {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val posts = TableQuery[PostTableDef]

  def add(post: Post): Future[String] = {
    dbConfig.db.run(posts += post).map(res => "Post successfully added").recover {
      case ex: Exception => ex.getCause.getMessage()
    }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(posts.filter(_.id === id).delete)
  }

  def get(id: Long): Future[Option[Post]] = {
    dbConfig.db.run(posts.filter(_.id === id).result.headOption)
  }

  def listsAll: Future[Seq[Post]] = {
    dbConfig.db.run(posts.result)
  }
}
