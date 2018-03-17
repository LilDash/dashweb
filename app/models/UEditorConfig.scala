package models

import java.io.FileInputStream
import java.nio.file.{Paths}

import play.Play
import play.api.libs.json.{JsValue, Json}


object UEditorConfig {


  val UEDITOR_CONFIG_FILE = Paths.get(
    Play.application().path().getPath, "conf", "ueditor.config.json").toString

  def get() : JsValue = {
    val stream = new FileInputStream(UEDITOR_CONFIG_FILE)
    try {  Json.parse(stream) } finally { stream.close() }
  }
}