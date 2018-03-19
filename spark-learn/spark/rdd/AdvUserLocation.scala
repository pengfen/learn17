package spark.rdd

import org.apache.spark.{SparkConf, SparkContext}
;

/**
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
  * ((fields(0),fields(2)), timeLong) -->reduceByKey(_+_).map --> (lac, (mobile, time))
  *     -->rdd1.join(rdd2).map-->(mobile, lac, time, x, y)
  *     --> groupBy().mapValues(以时间排序,取出前2个) --> (手机->((m,s,t)(m,s,t)))
  */
object AdvUserLocation {

  def main(args: Array[String]) {
    val conf = new SparkConf()
    conf.setAppName("AdvUserLocation").setMaster("local[2]")
    val sc = new SparkContext(conf)

    //val in = "file:///home/ricky/data/spark/basic/bs_user.log"
    val in = "/home/ricky/data/spark/basic/bs_user.log"
    //val in = "hdfs://ricky:9000/bs_user.log"
    val rdd0 = sc.textFile(in).map( line => {
      val fields = line.split(",")
      val eventType = fields(3)
      val time = fields(1)
      val timeLong = if(eventType == "1")  -time.toLong else time.toLong
      ((fields(0),fields(2)), timeLong)
    })
    //println(rdd0.collect().toBuffer) // ((16362717881,01882421fe59f8dbef3236d5c8320477),20180304204603)

    val rdd1 = rdd0.reduceByKey(_+_).map(t => {
      val mobile = t._1._1
      val lac = t._1._2
      val time = t._2
      (lac, (mobile, time))
    })

    //val in = "file:///home/ricky/data/spark/basic/base.log"
    val in_info = "/home/ricky/data/spark/basic/base.log"
    //val in = "hdfs://ricky:9000/base.log"
    val rdd2 = sc.textFile(in_info).map(line => {
      val f = line.split(",")
      //(基站ID， （经度，纬度）)
      (f(0), (f(1), f(2)))
    })
    //rdd1.join(rdd2)-->(CC0710CC94ECC657A8561DE549D940E0,((18688888888,1300),(116.303955,40.041935)))
    val rdd3 = rdd1.join(rdd2).map(t => {
      val lac = t._1
      val mobile = t._2._1._1
      val time = t._2._1._2
      val x = t._2._2._1
      val y = t._2._2._2
      (mobile, lac, time, x, y)
    })
    //rdd4分组后的
    val rdd4 = rdd3.groupBy(_._1)
    val rdd5 = rdd4.mapValues(it => {
      it.toList.sortBy(_._3).reverse.take(2)
    })
    //println(rdd1.join(rdd2).collect().toBuffer)
        println(rdd5.collect().toBuffer)
    //rdd5.saveAsTextFile("/home/ricky/data/out")
    sc.stop()
  }
}
