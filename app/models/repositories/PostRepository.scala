package models.repositories

import common.enums.PostStatus
import models.Post
import models.dal.PostTableDef
import play.api.{Logger, Play}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global


object PostRepository {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val posts = TableQuery[PostTableDef]

  def save(post: Post): Future[Int] = {
    dbConfig.db.run(posts.insertOrUpdate(post)).map(res =>
      res
    ).recover {
      case ex: Exception =>
        Logger.error(ex.getCause.getMessage())
        0
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

  def getNLatest(n: Int): Future[Seq[Post]] = {
    val action = posts.filter(p =>
      p.status === PostStatus.active.id
    ).sortBy(_.id.desc).take(n).result
    dbConfig.db.run(action)
  }
}
