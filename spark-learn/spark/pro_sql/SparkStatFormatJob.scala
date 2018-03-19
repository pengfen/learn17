package spark.pro_sql

import org.apache.spark.sql.SparkSession
import spark.pro_sql.utils.DateUtils

/**
  * 第一步清洗：抽取出我们所需要的指定列的数据
  *
  * 数据源 /home/ricky/data/spark/sql/access.log
  *
  */
object SparkStatFormatJob {

  def main(args: Array[String]) {

    val spark = SparkSession.builder().appName("SparkStatFormatJob")
      .master("local[2]").getOrCreate()

    //val in = "file:///home/ricky/data/spark/sql/access.log"
    //val in = "/home/ricky/data/spark/sql/access.log"
    //val in = "hdfs://ricky:9000/acces.log"
    val acccess = spark.sparkContext.textFile("file:///home/ricky/data/10000_access.log")
    // 测试读取文件前十行内容
    //acccess.take(10).foreach(println)

    acccess.map(line => {
      val splits = line.split(" ")
      val ip = splits(0) // 此打断点查看splits内容

      /**
        * 原始日志的第三个和第四个字段拼接起来就是完整的访问时间：
        * [10/Nov/2016:00:01:02 +0800] ==> yyyy-MM-dd HH:mm:ss
        */
      val time = splits(3) + " " + splits(4)
      val url = splits(11).replaceAll("\"","")
      val traffic = splits(9)
      //(ip, DateUtils.parse(time), url, traffic)
      DateUtils.parse(time) + "\t" + url + "\t" + traffic + "\t" + ip
    })// 打印前十行记录 格式(ip, DateUtils.parse(time), url, traffic) ---> (IP, 时间, 访问地址, 流量)
      //.take(10).foreach(println) //(183.162.52.7,2016-11-10 00:01:02,-,813)
    .saveAsTextFile("file:///home/ricky/data/output/")

    spark.stop()
  }

}
