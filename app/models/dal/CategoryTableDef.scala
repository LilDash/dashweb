package models.dal

import java.sql.Timestamp

import common.enums.CategoryStatus
import models.Category
import slick.jdbc.MySQLProfile.api._

class CategoryTableDef(tag: Tag) extends Table[Category](tag, "category"){
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def createTime = column[Timestamp]("create_time")
  def status = column[Int]("status")
  def description = column[Option[String]]("description")

  override def * =
    (
      id,
      name,
      createTime,
      status,
      description
    ) <> (
      {
        case (id, name, createTime, status, description) =>
          Category(id, name, createTime, CategoryStatus(status), description)
      },
      {
        c: Category => Some((c.id, c.name, c.createTime, c.status.id, c.description))
      }
    )
}
