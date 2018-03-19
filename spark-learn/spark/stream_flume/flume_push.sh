#!/bin/bash

# 运行代码 ---> 运行flume ---> telnet localhost 44444 ---> 输入测试内容 ---> 查看结果
spark-submit \
--class com.stream_flume.FlumePushWordCount \
--packages org.apache.spark:spark-streaming-flume_2.11:2.2.0 \
/home/ricky/spark-jar/spark-learn-1.0-jar-with-dependencies.jar \
ricky 41414

flume-ng agent \
--name simple-agent \
--conf $FLUME_HOME/conf \
--conf-file $FLUME_HOME/conf/flume_push_streaming.conf \
-Dflume.root.logger=INFO,console

