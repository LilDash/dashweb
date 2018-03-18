package common.utility

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date

object TimeUtil {
  def timeStampNow(): Timestamp = {
    val date = new Date()
    new Timestamp(date.getTime())
  }

  def formatTimestamp(timestamp: Timestamp, formatString: String = "yyyy-MM-dd HH:mm:ss") = {
    new SimpleDateFormat(formatString).format(timestamp)
  }
}
