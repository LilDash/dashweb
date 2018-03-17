package models.dal

import java.sql.Timestamp

import models.Image
import slick.jdbc.MySQLProfile.api._

class ImageTableDef(tag: Tag) extends Table[Image](tag, "image") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def key = column[String]("key")
  def createTime = column[Timestamp]("create_time")
  def url = column[String]("url")

  override def * =
    (
      id, key, createTime, url
    ) <> ((Image.apply _).tupled, Image.unapply)
}
