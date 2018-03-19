package spark.pro_subject

import java.net.URL

import org.apache.spark.sql.SparkSession
import spark.pro_subject.dao.SubjectDao
import spark.pro_subject.domain.Subject

import scala.collection.mutable.ListBuffer

/**
  * 取出学科点击前三的
  * 日期(年月日时分秒) 学科地址　　　　　　　
  * 20160321102628	http://java.itcast.cn/java/course/hadoop.shtml
  *
  * 数据源 /home/ricky/data/pro/subject/subject.log
  *
  * 1. 编写代码
  *
  * 2. 在spark-learn库中创建subject表
  * create table subject(subject varchar(255), url varchar(255), click_count int)
  engine=innodb default charset=utf8 comment='统计学科点击前三';
  */
object UrlCountClean {
  def main(args: Array[String]): Unit = {
    // spark1.6
//    val conf = new SparkConf()
//    conf.setAppName("UserLocationClean").setMaster("local[2]")
//
//    val sc = new SparkContext(conf)

    // spark2
    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()

    val in = "/home/ricky/data/pro/subject/subject.log"

    val subject = spark.sparkContext.textFile(in).map(line => {
      val splits = line.split("\t")
      (splits(1), 1)
    })//.take(10).foreach(println) // (http://net.cn/net/net.html,1)
      .reduceByKey(_ + _)//.take(10).foreach(println) // (http://php.cn/php/course/linux.html,62)
      .map(t => {
      val url = t._1
      val host = new URL(url).getHost
      (host, url, t._2)
    })//.take(10).foreach(println) // (java.cn,http://java.cn/java/course/javaee.html,75)

    import spark.implicits._
    val subjectDF = subject.toDF()
    val subjectTopNDF = subjectDF.orderBy($"_3".desc).limit(3)

    /**
      * 将统计结果写入到MySQL中
      */
    try {
      subjectTopNDF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[Subject]

        partitionOfRecords.foreach(info => {
          //(host, url, click_count)
          val host = info.getAs[String]("_1")
          val url = info.getAs[String]("_2")
          val click_count = info.getAs[Int]("_3")
          list.append(Subject(host, url, click_count))
        })

        SubjectDao.insertSubjectTonN(list)

      })
    } catch {
      case e:Exception => e.printStackTrace()
    }
  }
}
