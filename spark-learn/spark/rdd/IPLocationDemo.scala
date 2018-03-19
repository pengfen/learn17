package spark.rdd

import java.io.{BufferedReader, FileInputStream, InputStreamReader}

import scala.collection.mutable.ArrayBuffer
;

/**
  * IP地址处理
  *
  * 数据源 /home/ricky/data/spark/rdd/ip.txt
  */
object IPLocationDemo {

  def ip2Long(ip: String): Long = {
    val fragments = ip.split("[.]")
    var ipNum = 0L
    for (i <- 0 until fragments.length) {
      ipNum = fragments(i).toLong | ipNum << 8L
    }
    ipNum
  }

  def readData(path: String) = {
    val br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))
    var s: String = null
    var flag = true
    val lines = new ArrayBuffer[String]()

    while (flag) {
      s = br.readLine()
      if (s != null) {
        lines += s
      } else {
        flag = false
      }
    }
    lines
  }

  def binarySearch(lines: ArrayBuffer[String], ip: Long): Int = {
    var low = 0
    var high = lines.length - 1

    while (low <= high) {
      val middle = (low + high) / 2
      if ((ip >= lines(middle).split("\\|")(2).toLong) && (ip <= lines(middle).split("\\|")(3).toLong))
        return middle
      if (ip < lines(middle).split("\\|")(2).toLong)
        high = middle - 1
      else low = middle + 1
    }
    -1
  }

  def main(args: Array[String]): Unit = {
    val ip = "120.55.180.60"
    val ipNum = ip2Long(ip)
    println(ipNum) //2016916540
    val lines = readData("/home/ricky/data/spark/rdd/ip.txt")
    val index = binarySearch(lines, ipNum) //120.55.0.0|120.55.255.255|2016870400|2016935935|亚洲|中国|浙江|杭州||阿里巴巴|330100|China|CN|120.153576|30.287459
    //println(lines);
    println("index:" + index) // 45110
    println(lines(index))
  }
}
