package scala.basic

import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

object UrlCount {

  val updateFunc = (iterator: Iterator[(String, Seq[Int], Option[Int])]) => {
    iterator.flatMap{case(x,y,z) => Some(y.sum+z.getOrElse(0)).map(n=>(x,n))}
  }

  def main(args: Array[String]): Unit = {
    //LoggerLevel.setStreamingLogLevels()
//    val Array(zkQuorum, groupId, topics, numThreads, hdfs) = args
//    val conf = new SparkConf().setAppName("UrlCount")
//    val ssc = new StreamingContext(conf, Seconds(2))
//    ssc.checkpoint(hdfs)
//    val topicMap = topics.split(",").map((_,numThreads.toInt)).toMap
//    val lines = KafkaUtils.createStream(ssc, zkQuorum, groupId, topicMap, StorageLevel.MEMORY_AND_DISK).map(_._2)
//    val urls = lines.map(x=>(x.split(" ")(6), 1))
//    val result = urls.updateStateByKey(updateFunc, new HashPartitioner(ssc.sparkContext.defaultParallelism), true)
//    result.print()
//    ssc.start()
//    ssc.awaitTermination()
  }
}
