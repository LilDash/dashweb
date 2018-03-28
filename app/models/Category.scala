package models

import java.sql.Timestamp

import common.enums.CategoryStatus.CategoryStatus
import play.api.libs.json.{Json, Writes}



case class Category (
                      id: Long,
                      name: String,
                      createTime: Timestamp,
                      status: CategoryStatus,
                      description: Option[String],
                    )

object Category {
  implicit val categoryWrites = new Writes[Category]{
    def writes(category: Category) = Json.obj(
      "id" -> category.id,
      "name" -> category.name,
      "createTime" -> category.createTime.toString,
      "status" -> category.status.id,
      "description" -> category.description,
    )
  }
}
