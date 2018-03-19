#!/bin/bash

spark-submit \
--class spark.sql.HiveContextApp \
--master local[2] \
--jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar \
/home/ricky/spark-jar/spark-learn-1.0.jar
