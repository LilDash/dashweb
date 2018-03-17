package models

import java.sql.Timestamp

import play.api.libs.json.{Json, Writes}

case class Image (
                  id: Long,
                  key: String,
                  createTime: Timestamp,
                  url: String
                 )

object Image {
  implicit val imageWrites = new Writes[Image]{
    def writes(image: Image) = Json.obj(
      "id" -> image.id,
      "key" -> image.key,
      "url" -> image.url,
      "createTime" -> image.createTime.toString,
    )
  }
}