#!/bin/bash

# 1. 删除以前的输出目录
hadoop fs -rmr /out

# 2. 提交作业
spark-submit \
--class spark.basic.WordCountApp \
--master local[2] \
--jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar \
/home/ricky/spark-jar/spark-learn-1.0.jar \
hdfs://ricky:9000/wc.txt hdfs://ricky:9000/out

# 3. 查看结果
hadoop fs -cat /out/part*