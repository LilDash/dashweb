package models.repositories

import common.enums.CategoryStatus
import models.Category
import models.dal.CategoryTableDef
import play.api.{Logger, Play}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object CategoryRepository {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val categories = TableQuery[CategoryTableDef]

  def save(category: Category): Future[Int] = {
    dbConfig.db.run(categories.insertOrUpdate(category)).map(res =>
      res
    ).recover {
      case ex: Exception =>
        Logger.error(ex.getCause().getMessage())
        0
    }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(categories.filter(_.id === id).delete)
  }

  def get(id: Long): Future[Option[Category]] = {
    dbConfig.db.run(categories.filter(_.id === id).result.headOption)
  }

  def listsAll: Future[Seq[Category]] = {
    dbConfig.db.run(categories.result)
  }

  def listsAllActive: Future[Seq[Category]] = {
    dbConfig.db.run(categories.filter(_.status === CategoryStatus.active.id).result)
  }
}
