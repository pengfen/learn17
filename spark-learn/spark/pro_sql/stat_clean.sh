#!/bin/bash

spark-submit \
--class com.spark_project.SparkStatCleanJobYARN \
--name SparkStatCleanJobYARN \
--master yarn \
--executor-memory 1g \
--num-executors 1 \
--files /home/ricky/spark-jar/ipDatabase.csv,/home/ricky/spark-jar/ipRegion.xlsx \
/home/ricky/spark-jar/spark-learn-1.0-jar-with-dependencies.jar \
hdfs://ricky:9000/spark_sql/input hdfs://ricky:9000/spark_sql/clean