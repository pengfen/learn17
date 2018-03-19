#!/bin/bash

spark-submit \
--master local[2] \
--class com.spark_sql.SparkSessionApp \
/home/ricky/spark-jar/spark-learn-1.0.jar \
hdfs://ricky:9000/people.txt