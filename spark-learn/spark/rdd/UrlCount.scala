package spark.rdd

import java.net.URL

import org.apache.spark.{SparkConf, SparkContext}
;

/**
  * 取出学科点击前三的
  * 日期(年月日时分秒) 学科地址　　　　　　　
  * 20160321102628	http://java.itcast.cn/java/course/hadoop.shtml
  *
  * 数据源 /home/ricky/data/spark/rdd/subject.log
  */
object UrlCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("UrlCount").setMaster("local[2]")
    val sc = new SparkContext(conf)

    // rdd1将数据切分 元组中放的是 (URL, 1)
    //val in = "file:///home/ricky/data/spark/rdd/subject.log"
    val in = "/home/ricky/data/spark/rdd/subject.log"
    //val in = "hdfs://ricky:9000/subject.log"
    val rdd1 = sc.textFile(in).map(line => {
      val f = line.split("\t")
      (f(1), 1)
    })

    val rdd2 = rdd1.reduceByKey(_+_)

    val rdd3 = rdd2.map(t => {
      val url = t._1
      val host = new URL(url).getHost
      (host, url, t._2)
    })

    val rdd4 = rdd3.groupBy(_._1).mapValues(it => {
      it.toList.sortBy(_._3).reverse.take(3)
    })

    println(rdd2.collect().toBuffer) // (http://php.itcast.cn/php/course.shtml,459)
    sc.stop()
  }
}
