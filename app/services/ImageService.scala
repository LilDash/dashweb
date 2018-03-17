package services

import javax.inject.{Inject, Singleton}

import com.qiniu.common.Zone
import com.qiniu.util.Auth
import com.qiniu.storage.{BucketManager, UploadManager, Configuration => QiniuConfiguration}
import common.utility.TimeUtil
import models.Image
import models.repositories.ImageRepository
import play.api.{Configuration, Logger}
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

case class UploadImageResponse(
                                isSuccess: Boolean,
                                id: Long = 0,
                                fileName: String = "",
                                url: String = "",
                                message: String = "",
                              )

@Singleton
class ImageService @Inject()(config: Configuration){
  val IMAGE_STYLE_PARAM_SEPERATOR = "-"
  val IMAGE_STYLE_PREVIEW = "preview"

  def getImage(id: Long): Future[Option[Image]] = {
    ImageRepository.get(id)
  }

  def uploadImage(request: Request[AnyContent], name: String): Future[UploadImageResponse] = {
    // Get post data
    val postData = request.body.asMultipartFormData.get;
    // Get upload token
    val auth = Auth.create(
      config.get[String]("qiniu.accessKey"),
      config.get[String]("qiniu.secretKey"))
    val token = auth.uploadToken(config.get[String]("qiniu.bucket"))
    // Create upload manager
    val conf = new QiniuConfiguration(Zone.autoZone())
    val uploadManager = new UploadManager(conf)

    // process upload
    postData.file(name).map { file =>
      val tempFile = file.ref.getAbsolutePath()
      try {

        val response = uploadManager.put(tempFile, null, token)
        if (response.isOK){
          val responseData = response.jsonToMap()
          val fileKey = responseData.get("key").asInstanceOf[String]
          ImageRepository.get(fileKey).map { i =>
            if (i.isDefined) {
              // Image exists in database
              val imageRec = i.get
              UploadImageResponse(true, imageRec.id, fileKey, imageRec.url)
            } else {
              // Create a record in database
              val url = makeImageUrl(fileKey)
              val future = ImageRepository.add(Image(0, fileKey, TimeUtil.timeStampNow(), url)).map {id: Long =>
                if (id > 0){
                  UploadImageResponse(true, id, fileKey, url)
                } else {
                  UploadImageResponse(false, message = "Insert record failed")
                }
              }
              Await.result(future, Duration.Inf)
            }
          }
        } else {
          Future.successful(UploadImageResponse(false, message = "Upload image failed"))
        }
      } catch {
        case ex: Exception =>
          Logger.error(ex.getMessage())
          Future.successful(UploadImageResponse(false, message = "Failure with exception"))
      } finally {
        file.ref.delete();
      }
    }.getOrElse(Future.successful(UploadImageResponse(false, message = "Unable to get uploaded file")))
  }

  def deleteImage(key: String): Future[Boolean] = {
    val auth = Auth.create(
      config.get[String]("qiniu.accessKey"),
      config.get[String]("qiniu.secretKey"))
    val conf = new QiniuConfiguration(Zone.autoZone())
    val bucketManager = new BucketManager(auth, conf)
    val response = bucketManager.delete(config.get[String]("qiniu.bucket"), key)
    if (!response.isOK) {
      Logger.error(response.error)
      Future.successful(response.isOK)
    } else {
      ImageRepository.delete(key).map { res =>
        res > 0
      }
    }
  }

  def makeImageUrl(fileKey: String): String = {
    val domain = config.get[String]("qiniu.domain")
    s"http://${domain}/${fileKey}"
  }

  def getPreviewUrl(origin: String): String = {
    s"${origin}${IMAGE_STYLE_PARAM_SEPERATOR}${IMAGE_STYLE_PREVIEW}"
  }

}
