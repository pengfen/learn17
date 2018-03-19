package spark.pro_user

import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.types._
import org.apache.spark.{SparkConf, SparkContext}
import spark.pro_user.domain.User
import spark.utils.DateUtils

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
object UserLocationClean {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("UserLocationClean").setMaster("local[2]")

    val sc = new SparkContext(conf)

    val in_user = "/home/ricky/data/pro/user/bs_user.log"
    val in_info = "/home/ricky/data/spark/basic/base.log"
    val out = "/home/ricky/data/spark-out/basic"

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
      val mobile = t._2._1._1
      val time = t._2._1._2
      val x = t._2._2._1
      val y = t._2._2._2
      val day = DateUtils.getCurrTime()
      //(mobile, bs, time, x, y)
      day + "," + mobile + "," + bs + "," + time + "," + x + "," + y
    })//.take(10).foreach(println)
        .saveAsTextFile(out)

//    val a = result.groupBy(_._1).mapValues(it => {
//      it.toList.sortBy(_._3).reverse.take(2)
//    })//.saveAsTextFile(out)
    //println(a.collect.toBuffer) // ArrayBuffer((13239267333,List((13239267333,2238a1063b7345745cd8dec645186a6a,940023,129.569844,285.823592))), (14725998966,List((14725998966,086b7854a
    //println(base.join(user).collect.toBuffer) // ArrayBuffer((eae376b55bc4e20feafc0795148d4a1f,((78.494732,358.554567),(16459869135,107720))),

    sc.stop()
  }
}
