package common.utility

import java.sql.Timestamp
import java.util.Date

object TimeUtil {
  def timeStampNow(): Timestamp ={
    val date = new Date()
    new Timestamp(date.getTime())
  }
}
