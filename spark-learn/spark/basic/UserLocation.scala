package spark.basic

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 根据日志统计出每个用户在站点所呆时间最长的前2个的信息
  *
  * 用户日志信息
  * 手机号       时间(年月日时分秒) 基站标识(32位)　　　　　　　　　　　 １表示进入基站 0表示离开基站
  * 18688888888,20160327082400,16030401EAFB68F1E3CDF819735E1C66,1
  *
  * 数据源 /home/ricky/data/spark/basic/bs_user.log
  *
  * 基站日志信息
  * 基站标识                          经度        纬度
  * 9F36407EAD0629FC166F14DDE7970F68,116.304864,40.050645,6
  * 数据源 /home/ricky/data/spark/basic/base.log
  *
  */
object UserLocation {
  def main(args: Array[String]): Unit = {
    //spark2以前
//    val conf = new SparkConf()
//    conf.setAppName("UserLocation").setMaster("local[2]")
//
//    val sc = new SparkContext(conf)

    // spark2
    val spark = SparkSession.builder().appName("SparkSessionApp")
      .master("local[2]").getOrCreate()
    val sc = spark.sparkContext;

    //val in = "file:///home/ricky/data/spark/basic/bs_user.log"
    val in = "/home/ricky/data/spark/basic/bs_user.log"
    //val in = "hdfs://ricky:9000/bs_user.log"
    // val rdd1 = sc.textFile(in)
    // 18688888888,20160327082400,16030401EAFB68F1E3CDF819735E1C66,1
    // rdd1.take(10).foreach(println)

    val rdd1 = sc.textFile(in).map(x => {
      val arr = x.split(",")
      val mb = (arr(0), arr(2)) // 此打断点查看arr内容
      val flag = arr(3)
      var time = arr(1).toLong
      if (flag == "1") time = -time
      (mb, time)
    })
    //.take(10).foreach(println) // ((18688888888,16030401EAFB68F1E3CDF819735E1C66),-20160327082400)
    //println(rdd1); // /home/ricky/data/spark/basic/user.log MapPartitionsRDD[1] at textFile at UserLocation.scala:23

    val rdd2 = rdd1.reduceByKey(_+_) // ((手机号,基站), 总时间)
    println(rdd2) // ShuffledRDD[3] at reduceByKey at UserLocation.scala:38

    //val in = "file:///home/ricky/data/spark/basic/base.log"
    val in_info = "/home/ricky/data/spark/basic/base.log"
    //val in = "hdfs://ricky:9000/base.log"
    val rdd3 = sc.textFile(in_info).map(x => {
      val arr = x.split(",")
      val bs = arr(0)
      (bs, (arr(1), arr(2)))
    })
      //.take(10).foreach(println) // (9F36407EAD0629FC166F14DDE7970F68,(116.304864,40.050645))
    println(rdd3) // MapPartitionsRDD[6] at map at UserLocation.scala:51

    // t._1 ---> (手机号,基站标识) t._2 ---> 总时间 t._._2 ---> 基站标识 t._1._1 ---> 手札号
    val rdd4 = rdd2.map(t => (t._1._2, (t._1._1, t._2))) // (基站标识,(手札号,停留时间))
    println(rdd4) // MapPartitionsRDD[7] at map at UserLocation.scala:59

    val rdd5 = rdd4.join(rdd3) // (基站标识, ((手机号,停留时间), (经度, 纬度))　
    println(rdd5)

    val rdd6 = rdd2.map(t => (t._1._1, t._1._2, t._2)).groupBy(_._1).values.map(it => {
      it.toList.sortBy(_._3).reverse
    })

    // (基站标识, ((手机号,停留时间), (经度, 纬度))　
    //ArrayBuffer((16030401EAFB68F1E3CDF819735E1C66,((18611132889,97500),(116.296302,40.032296))), (16030401EAFB68F1E3CDF819735E1C66,((18688888888,87600),(116.296302,40.032296))))
    println(rdd5.collect.toBuffer)
  }
}
