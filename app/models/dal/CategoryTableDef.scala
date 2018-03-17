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

  override def * =
    (
      id,
      name,
      createTime,
      status
    ) <> (
      {
        case (id, name, createTime, status) => Category(id, name, createTime, CategoryStatus(status))
      },
      {
        c: Category => Some((c.id, c.name, c.createTime, c.status.id))
      }
    )
}
