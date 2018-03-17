package services

import javax.inject.Inject
import javax.inject.Singleton

import models.{EditorUploadResponse, UEditorConfig}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class EditorService @Inject()(imageService: ImageService){

  def getEditorConfig() : JsValue = {
    UEditorConfig.get()
  }

  def uploadImage(request: Request[AnyContent]): Future[JsValue] = {
    imageService.uploadImage(request, "upfile").map { uploadResp =>
      val result  = if (uploadResp.isSuccess){
        EditorUploadResponse(
          "SUCCESS",
          uploadResp.url,
          uploadResp.fileName,
          uploadResp.fileName)
      } else {
        EditorUploadResponse("FAILED")
      }
      Json.toJson(result)
    }
  }
}
