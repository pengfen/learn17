#!/bin/bash

spark-submit \
--class spark.sql.SQLContextApp \
--master local[2] \
/home/ricky/spark-jar/spark-learn-1.0.jar \
hdfs://ricky:9000/people.json