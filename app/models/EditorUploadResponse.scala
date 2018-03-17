package models

import play.api.libs.json.{Json}

case class EditorUploadResponse(
                                state: String,
                                url: String = "",
                                title: String = "",
                                original: String = "")


object EditorUploadResponse {
  implicit val uploadResponseFormat = Json.format[EditorUploadResponse]
}