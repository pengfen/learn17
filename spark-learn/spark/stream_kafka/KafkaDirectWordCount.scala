package spark.stream_kafka

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Spark Streaming对接Kafka的方式二 Direct方式
  *
  * 打包 mvn clean package -DskipTests
  *
  *ricky@ricky:~/app/kafka_2.11-0.9.0.0/bin$ ./kafka-console-producer.sh --broker-list localhost:9092 --topic kafka_streaming_topic
Picked up _JAVA_OPTIONS:   -Dawt.useSystemAAFontSettings=gasp
a a a a
b b b
c c
d

  查看控制台
  (a,4)
  (d,1)
(b,3)
(c,2)
  */
object KafkaDirectWordCount {
  def main(args: Array[String]): Unit = {

    if (args.length != 2) {
      // ricky:9092 kafka_streaming_topic
      System.err.println("Usage: KafkaDirectWordCount <brokers> <topics> ")
      System.exit(1)
    }

    val Array(brokers, topics) = args

    val sparkConf = new SparkConf()
    //sparkConf.setAppName("KafkaDirectWordCount").setMaster("local[2]")

    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val topicsSet = topics.split(",").toSet

    val kafkaParams = Map[String,String]("metadata.broker.list"-> brokers)

    val messages = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](
      ssc,kafkaParams,topicsSet
    )

    messages.map(_._2).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

    ssc.start()
    ssc.awaitTermination()
  }
}
