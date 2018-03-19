package spark.stream_kafka

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Spark Streaming 整合 kafka 方式之一 之 Receiver整合
1. 启动zk ./zkServer.sh start
2. 启动kafka ./kafka-server-start.sh -daemon /home/ricky/app/kafka_2.11-0.9.0.0/config/server.properties
3. 创建topic
./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka_streaming_topic

./kafka-topics.sh --list --zookeeper localhost:2181 # 查看topic

4. 通过控制台测试本topic是否能够正常的生产和消费
./kafka-console-producer.sh --broker-list localhost:9092 --topic kafka_streaming_topic

./kafka-console-consumer.sh --zookeeper localhost:2181 --topic kafka_streaming_topic

ricky:2181 test kafka_streaming_topic 1

  运行程序

ricky@ricky:~/app/kafka_2.11-0.9.0.0/bin$ ./kafka-console-producer.sh --broker-list localhost:9092 --topic kafka_streaming_topic
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
  *
  * mvn clean package -DskipTests
  */
object KafkaRecevierWordCount {
  def main(args: Array[String]): Unit = {

    if (args.length != 4) {
      // ricky:2181 test kafka_streaming_topic 1
      System.err.println("Usage: KafkaReceiverWordCount <zkQuorum> <group> <topics> <numThreads>")
    }

    val Array(zkQuorum, group, topics, numThreads) = args

    val sparkConf = new SparkConf()
    //sparkConf.setAppName("KafkaRecevierWordCount").setMaster("local[2]")

    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val  topicMap = topics.split(",").map((_, numThreads.toInt)).toMap

    // Spark Streaming 如何对接 Kafka
    val messages = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)

    // 测试 messages 结构
    messages.map(_._2).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

    ssc.start()
    ssc.awaitTermination()
  }
}
