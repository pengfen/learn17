#!/bin/bash

spark-submit \
--class com.spark_project.TopNStatJobYARN \
--name TopNStatJobYARN \
--master yarn \
--executor-memory 1g \
--num-executors 1 \
/home/ricky/spark-jar/spark-learn-1.0-jar-with-dependencies.jar \
hdfs://ricky:9000/spark_sql/clean 20170511