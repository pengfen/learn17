package spark.stream_flume

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
;

/**
  * Spark Streaming整合Flume的第一种方式之push
  *
  * 运行 FlumePushWordCount
  *
  * flume-ng agent \
  * --name simple-agent \
  * --conf $FLUME_HOME/conf \
  * --conf-file $FLUME_HOME/conf/flume_push_streaming.conf \
  * -Dflume.root.logger=INFO,console
  *
  * deepin 安装 telnet
  *
  * sudo apt-get install telnet
  *
  * telnet localhost 44444
  *
  * 使用参数来解决硬代码(hostname, port)
  * edit configurations
  * program arguments: ricky 41414
  *
  * 再次运行代码
  *
  * telnet localhost 44444
  * d
OK
d
OK
d
OK
c
OK
c
OK
c
OK
c

  查看代码控制台
  * (d,3)
    (c,4)
  */
object FlumePushWordCount {
  def main(args: Array[String]): Unit = {

    if (args.length != 2) {
      // ricky 41414
      System.err.println("Usage: FlumePushWordCount <hostname> <port>")
      System.exit(1)
    }

    val Array(hostname, port) = args

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("FlumePushWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    // 如何使用SparkStreaming整合Flume
    //val flumeStream = FlumeUtils.createStream(ssc, "ricky", 41414)
    // 使用参数
    val flumeStream = FlumeUtils.createStream(ssc, hostname, port.toInt)

    flumeStream.map(x=> new String(x.event.getBody.array()).trim)
      .flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

    ssc.start()
    ssc.awaitTermination()
    //ssc.stop()
  }
}
