#!/bin/bash

# 运行flume ---> 运行代码 ---> telnet localhost 44444 ---> 输入测试内容 ---> 查看结果
flume-ng agent \
--name simple-agent \
--conf $FLUME_HOME/conf \
--conf-file $FLUME_HOME/conf/flume_pull_streaming.conf \
-Dflume.root.logger=INFO,console

spark-submit \
--master local[2] \
--class com.stream_flume.FlumePullWordCount \
--packages org.apache.spark:spark-streaming-flume_2.11:2.2.0 \
/home/ricky/spark-jar/spark-learn-1.0-jar-with-dependencies.jar \
ricky 41414
