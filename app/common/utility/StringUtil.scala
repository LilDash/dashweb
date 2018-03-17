package common.utility

object StringUtil {

  def sanitizeUploadedText(text: String): String ={
    return text.replaceAll("(\u0000|\r|\n)", "")
  }
}
