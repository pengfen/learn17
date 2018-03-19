package spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
;

/**
  * 黑名单过滤
  *
  * 输入
  * 20180808,zs
  * ...
  *
  * _2 第二下标对应的值
  *
  * 访问日志 ===> DStream
  * 20180219,zs
  * 20180219,ls
  * 20180219,ww
  * ===> (zs: 20180219,zs)(ls: 20180219,ls)(ww: 20180219,ww)
  *
  * 黑名单列表 ===> RDD zs ls
  * ===> (zs: true)(ls: true)
  *
  * leftjoin
  * (zs: <20180219,zs>, <true>)
  * (ls: <20180219,ls>, <true>)
  * (ww: <20180219,ww>, <false>)
  *
  * 端口监听 nc -lk 9999
  *
  * 运行代码
  *
  * 输入
  * 20180219,zs
  * 20180219,ls
  * 20180219,ww
  */
object TransformApp {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("TransformApp")

    /**
      * 创建StreamingContext需要两个参数：SparkConf和batch interval
      */
    val ssc = new StreamingContext(sparkConf, Seconds(5))


    /**
      * 构建黑名单
      */
    val blacks = List("zs", "ls")
    val blacksRDD = ssc.sparkContext.parallelize(blacks).map(x => (x, true))

    val lines = ssc.socketTextStream("localhost", 6789)
    val clicklog = lines.map(x => (x.split(",")(1), x)).transform(rdd => {
      rdd.leftOuterJoin(blacksRDD)
        .filter(x=> x._2._2.getOrElse(false) != true)
        .map(x=>x._2._1)
    })

    clicklog.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
