#!/bin/bash

# 启动 zk ---> 启动kafka ---> 启动生产者 ---> 运行代码 ---> 生产端输入统计文字 ---> 控制台查看结果
spark-submit \
--master local[2] \
--class com.stream_kafka.KafkaRecevierWordCount \
--name KafkaRecevierWordCount \
--packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.2.0 \
/home/ricky/spark-jar/spark-learn-1.0-jar-with-dependencies.jar \
ricky:2181 test kafka_streaming_topic 1
