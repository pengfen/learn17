#!/bin/bash

# 1. 获取今天的日期
# date+'%Y-%m-%d'或者date -d "now" +%Y-%m-%d

# 2. 获取昨天的日期
# date -d "yesterday" +%Y-%m-%d或者date -d "1 days ago" +%Y-%m-%d

# 3. 获取前天的日期
# date -d "1 days ago" +%Y-%m-%d

# 4. 获取具体日期的前几天
# date -d"15 day ago 2017-04-16" +%Y-%m-%d

#获取当前日期 如20160903
#date_now=`date +%Y%m%d`
date_yes=`date -d "yesterday" +%Y%m%d`

# 1. 先备份数据 bs_user.log.bak_20180304
#cp -a /home/ricky/data/spark/basic/bs_user_${date_now}.log /home/ricky/data/spark/basic/bs_user._${date_now}.log.bak
cp -a /home/ricky/data/spark/basic/bs_user_${date_yes}.log /home/ricky/data/spark/basic/bs_user._${date_yes}.log.bak

# 2. 移到数据至data/pro/user
#mv /home/ricky/data/spark/basic/bs_user_${date_now}.log /home/ricky/data/pro/user
mv /home/ricky/data/spark/basic/bs_user_${date_yes}.log /home/ricky/data/pro/user

# 3. 创建目录
/home/ricky/app/hadoop-2.6.0-cdh5.7.0/bin/hadoop fs -mkdir -p /user/input/${date_yes}

# 4. 提交日志至hdfs上
/home/ricky/app/hadoop-2.6.0-cdh5.7.0/bin/hadoop fs -put /home/ricky/data/pro/user/bs_user_${date_yes}.log /user/input/${date_yes}

# 5. spark作业
/home/ricky/app/spark-2.2.0-bin-2.6.0-cdh5.7.0/bin/spark-submit \
--class spark.pro_user.UserLocationYARN \
--name UserLocationYARN \
--master yarn \
--executor-memory 1g \
--num-executors 1 \
--jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar \
/home/ricky/spark-jar/spark-learn-1.0.jar \
hdfs://ricky:9000/user/input/${date_now} hdfs://ricky:9000/base.log
