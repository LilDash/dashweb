package models.repositories


import models.Image
import models.dal.{ImageTableDef, PostTableDef}
import play.api.{Logger, Play}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object ImageRepository {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val images = TableQuery[ImageTableDef]

  def add(image: Image): Future[Long] = {
    val query = (images returning images.map(_.id)) += image
    dbConfig.db.run(query).map { res: Long =>
      res
    }.recover {
      case ex: Exception =>
        Logger.error(ex.getMessage)
        0
    }
  }

  def save(image: Image): Future[Int] = {

    dbConfig.db.run(images.insertOrUpdate(image)).map { res =>
      val imageId = (images returning images.map(_.id)) += image
      imageId.asInstanceOf[Int]
    }.recover {
      case ex: Exception =>
        Logger.error(ex.getMessage)
        0
    }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(images.filter(_.id === id).delete)
  }

  def delete(key: String): Future[Int] = {
    dbConfig.db.run(images.filter(_.key === key).delete)
  }

  def get(id: Long): Future[Option[Image]] = {
    dbConfig.db.run(images.filter(_.id === id).result.headOption)
  }

  def get(key: String): Future[Option[Image]] = {
    dbConfig.db.run(images.filter(_.key === key).result.headOption)
  }
}
