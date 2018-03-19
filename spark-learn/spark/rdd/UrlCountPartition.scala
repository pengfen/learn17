package com.spark_rdd

import java.net.URL

import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

/**
  * 日期(年月日时分秒) 学科地址　　　　　　　
  * 20160321102628	http://java.itcast.cn/java/course/hadoop.shtml
  *
  * 数据源 /home/ricky/data/spark/rdd/subject.log
  */
object UrlCountPartition {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("UrlCountPartition").setMaster("local[2]")
    val sc = new SparkContext(conf)

    // rdd1将数据切分 元组中放的是 (URL, 1)
    //val in = "file:///home/ricky/data/spark/rdd/subject.log"
    val in = "/home/ricky/data/spark/rdd/subject.log"
    //val in = "hdfs://ricky:9000/subject.log"
    val rdd1 = sc.textFile(in).map(line => {
      val f = line.split("\t")
      (f(1), 1)
    })
    val rdd2 = rdd1.reduceByKey(_ + _)

    val rdd3 = rdd2.map(t => {
      val url = t._1
      val host = new URL(url).getHost
      (host, (url, t._2))
    })

    val ints = rdd3.map(_._1).distinct().collect()

    // hashParitioner  相同Key的数据一定会在同一个reducer中，一个reducer中就只有一个Key
    val hostParitioner = new HostParitioner(ints)

    //val rdd4 = rdd3.partitionBy(new HashPartitioner(ints.length))

    val rdd4 = rdd3.partitionBy(hostParitioner).mapPartitions(it => {
      it.toList.sortBy(_._2._2).reverse.take(2).iterator
    })

    //rdd4.saveAsTextFile("/home/ricky/data/spark-out/rdd")

    println(rdd4.collect().toBuffer) //ArrayBuffer((net.itcast.cn,(http://net.itcast.cn/net/course.shtml,521))
    sc.stop()
  }
}

/**
  * 决定了数据到哪个分区里面
  * @param ins
  */
class HostParitioner(ins: Array[String]) extends Partitioner {
  val parMap = new mutable.HashMap[String, Int]()
  var count = 0
  for (i <- ins) {
    parMap += (i -> count)
    count += 1
  }

  // 分区数量
  override def numPartitions: Int = ins.length

  // 得到分区
  override def getPartition(key: Any): Int = {
    parMap.getOrElse(key.toString, 0)
  }
}

