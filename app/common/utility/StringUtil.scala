package common.utility

object StringUtil {

  def sanitizeUploadedText(text: String): String ={
    return text.replaceAll("(\0|\r|\n)", "")
  }
}
