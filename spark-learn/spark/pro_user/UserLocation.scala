package spark.pro_user

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import spark.pro_user.dao.UserDao
import spark.pro_user.domain.User
import spark.utils.DateUtils

import scala.collection.mutable.ListBuffer

/**
  * 根据日志统计出每个用户在站点所呆时间最长的前2个的信息
  *
  * 用户日志信息
  * 手机号       时间(年月日时分秒) 基站标识(32位)　　　　　　　　　　　 １表示进入基站 0表示离开基站
  * 18688888888,20160327082400,16030401EAFB68F1E3CDF819735E1C66,1
  *
  * 数据源 /home/ricky/data/pro/user/bs_user.log
  *
  * 基站日志信息
  * 基站标识                          经度        纬度
  * 9F36407EAD0629FC166F14DDE7970F68,116.304864,40.050645,6
  * 数据源 /home/ricky/data/spark/basic/base.log
  *
  * 1. 编写代码
  * 测试map中的数据(使用take(10).foreach(println)或使用print(user.collect.toBuffer))
  *
  */
object UserLocation {

  def main(args: Array[String]): Unit = {
    //spark1.6
//    val conf = new SparkConf()
//    conf.setAppName("UserLocationClean").setMaster("local[2]")
//
//    val sc = new SparkContext(conf)

    //spark2
    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()
    val sc = spark.sparkContext;

    //val day = DateUtils.getCurrTime();
    val day = DateUtils.getYestTime()

    val in_user = "/home/ricky/data/pro/user/bs_user_" + day + ".log"
    val in_info = "/home/ricky/data/spark/basic/base.log"

    // 1. 读取用户日志
    val user = sc.textFile(in_user).map(x => {
      val splits = x.split(",")
      val mb = (splits(0), splits(2))
      val flag = splits(3)
      var time = splits(1).toLong
      if (flag == "1") time = -time
      (mb, time)
    }).reduceByKey(_ + _).map(t => {
      val mobile = t._1._1
      val bs = t._1._2
      val time = t._2
      (bs, (mobile, time))
    })
    //.take(10).foreach(println) //((17868569437,fd66289b5d5da767d9e5e47e7dc7ff41),-20180304123401)
      //.reduceByKey(_ + _)//.take(10).foreach(println) //((14364215117,d02ab8b10881ac71169c1046a5c8f72f),917648)
      //.map(t => (t._1._2, (t._1._1, t._2)))//.take(10).foreach(println) //(d02ab8b10881ac71169c1046a5c8f72f,(14364215117,917648))

    // 2. 读取基站日志
    val base = sc.textFile(in_info).map(x => {
      val splits = x.split(",")
      val bs = splits(0)
      (bs, (splits(1), splits(2)))
    })//.take(10).foreach(println) // (b8b16e4373cc69e6a21df56c3b5bf704,(59.515617,23.985352))

    val result = user.join(base).map(t => {
      val bs = t._1
      val mobile = t._2._1._1.toLong
      val time = t._2._1._2.toString
      val x = t._2._2._1.toDouble
      val y = t._2._2._2.toDouble
      (day, mobile, bs, time, x, y)
    })
    // val personRDD = lineRDD.map(x => Person(x(0).toInt, x(1), x(2).toInt))
//    val result = user.join(base).map(x => User(DateUtils.getCurrTime(), x._2._1._1.toLong, x._1,
//      x._2._1._2.toString, x._2._2._1.toDouble, x._2._2._2.toDouble))

    import spark.implicits._
    val userDF = result.toDF()
    // (day, mobile, bs, time, x, y) ---> (_1, _2, _3, _4, _5, _6)
    val userTopNDF = userDF.orderBy($"_4".desc).limit(10)

    //userTopNDF.show()

    /**
      * 将统计结果写入到MySQL中
      */
    try {
      userTopNDF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[User]

        partitionOfRecords.foreach(info => {
          //(mobile, bs, time, x, y)
          val day = info.getAs[String]("_1") // day
          val mobile = info.getAs[Long]("_2") // mobile
          val bs = info.getAs[String]("_3") // bs
          val time = info.getAs[String]("_4") // time
          // java.lang.String cannot be cast to java.lang.Double 对于错误在result中进行强转
          val x = info.getAs[Double]("_5") // x
          val y = info.getAs[Double]("_6") // y
          list.append(User(day, mobile, bs, time, x, y))
        })

        UserDao.insertUserTonN(list)
      })
    } catch {
      case e:Exception => e.printStackTrace()
    }

    sc.stop()
  }
}
