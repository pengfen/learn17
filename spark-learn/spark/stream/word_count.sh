#!/bin/bash

# 先使用 nc -lk 9999 监听端口
spark-submit \
--master local[2] \
--class org.apache.spark.examples.streaming.NetworkWordCount \
--name NetworkWordCount \
/home/ricky/app/spark-2.2.0-bin-2.6.0-cdh5.7.0/examples/jars/spark-examples_2.11-2.2.0.jar ricky 9999
