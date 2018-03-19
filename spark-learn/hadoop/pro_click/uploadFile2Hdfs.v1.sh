#!/bin/bash

# set java env (设置java环境)
export JAVA_HOME=/home/ricky/app/jdk1.8.0_144
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH

#set hadoop env (设置hadoop环境)
export HADOOP_HOME=/home/ricky/app/hadoop-2.6.0-cdh5.7.0
export PATH=${HADOOP_HOME}/bin:${HADOOP_HOME}/sbin:$PATH

#日志文件存放的目录
log_src_dir=/home/ricky/logs

#日志文件上传到hdfs的根路径
hdfs_root_dir=/logs/hadoop

#读取日志文件的目录，判断是否有需要上传的文件

ls $log_src_dir | while read fileName
do
        if [ "hadoop.log1" = "$fileName" ];then
                hadoop fs -put $log_src_dir$fileName $hdfs_root_dir
        fi
done