package spark.utils

import java.util.{Calendar, Date, GregorianCalendar}

import org.apache.commons.lang3.time.FastDateFormat

/**
  * 日期时间工具类
  */
object DateUtils {

  val YYYYMMDDHHMMSS_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")
  val YYYYMMDD_FORMAT = FastDateFormat.getInstance("yyyyMMdd")
  val TARGE_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmss")


  def getTime(time : String) = {
    YYYYMMDDHHMMSS_FORMAT.parse(time).getTime
  }

  def parseToMinute(time : String) = {
    TARGE_FORMAT.format(new Date(getTime(time)))
  }

  def getCurrTime() = {
    YYYYMMDD_FORMAT.format(new Date())
  }

  def getYestTime() = {
    val date : Date = new Date()
    val calendar = new GregorianCalendar()
    calendar.setTime(date)
    calendar.add(Calendar.DATE, -1)
    YYYYMMDD_FORMAT.format(calendar.getTime)
  }

  def main(args: Array[String]): Unit = {
    // 测试运行
    //println(parseToMinute("2017-10-22 14:46:01"))

    println(getCurrTime())

    println(getYestTime)

  }

}