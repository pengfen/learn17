package spark.rdd

import org.apache.spark.{SparkConf, SparkContext}
;

/**
  * 根据日志统计出每个用户在站点所呆时间最长的前2个的信息
  *   1, 先根据"手机号_站点"为唯一标识, 算一次进站出站的时间, 返回(手机号_站点, 时间间隔)
  *   2, 以"手机号_站点"为key, 统计每个站点的时间总和, ("手机号_站点", 时间总和)
  *   3, ("手机号_站点", 时间总和) --> (手机号, 站点, 时间总和)
  *   4, (手机号, 站点, 时间总和) --> groupBy().mapValues(以时间排序,取出前2个) --> (手机->((m,s,t)(m,s,t)))
  *
  *   手机号       时间(年月日时分秒) 基站标识　　　　　　　　　　　　　　　１表示进入基站 0表示离开基站
  *   18688888888,20160327082400,16030401EAFB68F1E3CDF819735E1C66,1
  *
  *   数据源 /home/ricky/data/spark/basic/bs_user.log
  */
object UserLocation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("UserLocation").setMaster("local[2]")
    val sc = new SparkContext(conf)

    // sc.textFile("/home/ricky/data/log_2.log").map(_.split(",")).map(x => (x(0), x(1), x(2), x(3)))
    //val in = "file:///home/ricky/data/spark/basic/bs_user.log"
    val in = "/home/ricky/data/spark/basic/bs_user.log"
    //val in = "hdfs://ricky:9000/bs_user.log"
    val mbt = sc.textFile(in).map(line => {
      val fields = line.split(",")
      val eventType = fields(3)
      val time = fields(1)
      val timeLong = if (eventType == "1") -time.toLong else time.toLong
      (fields(0) + "_" + fields(2), timeLong)
    })
      //.take(10).foreach(println) // (16354351686_b1d2395eb520420ad9676b62bf102f61,-20180304123501)

    //println(mbt.collect().toBuffer)
    //(18611132889_9F36407EAD0629FC166F14DDE7970F68,54000)
    //println(mbt.groupBy(_._1).collect().toBuffer) //(16152299113_99a320e81d6b5fc34e74266ae4355c3b,CompactBuffer((16152299113_99a320e81d6b5fc34e74266ae4355c3b,-201803041322
    val rdd1 = mbt.groupBy(_._1).mapValues(_.foldLeft(0L)(_ + _._2))

    val rdd2 = rdd1.map( t => {
      val mobile_bs = t._1 // 此打断点查看t元素内容
      val mobile = mobile_bs.split("_")(0)
      val lac = mobile_bs.split("_")(1)
      val time = t._2
      (mobile, lac, time) //(手机,　基站, 总时间和) ---＞ (18611132889,9F36407EAD0629FC166F14DDE7970F68,54000)
    })

    val rdd3 = rdd2.groupBy(_._1)

    //ArrayBuffer((18688888888,List((18688888888,16030401EAFB68F1E3CDF819735E1C66,87600), (18688888888,9F36407EAD0629FC166F14DDE7970F68,51200))), (18611132889,List((18611132889,16030401EAFB68F1E3CDF819735E1C66,97500), (18611132889,9F36407EAD0629FC166F14DDE7970F68,54000))))
    val rdd4 = rdd3.mapValues(it => {
      it.toList.sortBy(_._3).reverse.take(2)
    })
    println(rdd4.collect().toBuffer)

    sc.stop()
  }
}
