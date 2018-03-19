spark streaming 整合 flume,kafka

python ---生成--- 日志 ---> flume收集 ---> kafka ---> spark streaming

1. 编写脚本 generate_log.py

2. 编写执行脚本 log_generate.sh
#!/bin/bash

# 注意 使用全路径 (crontab不使用全路径无效) which python
/usr/bin/python /home/ricky/data/generate_log.py

3. 编写定时任务
ricky@ricky:~/data$ crontab -l

*/1 * * * * /home/ricky/data/log_generate.sh # 每秒产生十条日志

4. 监控日志文件是否产生
cd /home/ricky/data/logs

tail -f access.log

5. 编写flume配置文件 vi streaming_project.conf

exec-memory-logger.sources = exec-source
exec-memory-logger.sinks = logger-sink
exec-memory-logger.channels = memory-channel

exec-memory-logger.sources.exec-source.type = exec
exec-memory-logger.sources.exec-source.command = tail -F /home/ricky/data/logs/access.log
exec-memory-logger.sources.exec-source.shell = /bin/sh -c

exec-memory-logger.channels.memory-channel.type = memory

exec-memory-logger.sinks.logger-sink.type = logger

exec-memory-logger.sources.exec-source.channels = memory-channel
exec-memory-logger.sinks.logger-sink.channel = memory-channel

6. 执行flume
flume-ng agent \
--name exec-memory-logger \
--conf $FLUME_HOME/conf \
--conf-file /home/ricky/app/flume-1.6.0-cdh5.7.0/conf/streaming_project.conf \
-Dflume.root.logger=INFO,console

7. 启动zk
cd $ZK_HOME
bin/zkServer.sh start

8. 启动Kafka
cd $KAFKA_HOME
bin/kafka-server-start.sh -daemon /home/ricky/app/kafka_2.11-0.9.0.0/config/server.properties

9. 修改flume配置文件使用flume sink数据到kafka
streaming_project2.conf
exec-memory-kafka.sources = exec-source
exec-memory-kafka.sinks = kafka-sink
exec-memory-kafka.channels = memory-channel

exec-memory-kafka.sources.exec-source.type = exec
exec-memory-kafka.sources.exec-source.command = tail -F /home/hadoop/data/logs/access.log
exec-memory-kafka.sources.exec-source.shell = /bin/sh -c

exec-memory-kafka.channels.memory-channel.type = memory

exec-memory-kafka.sinks.kafka-sink.type = org.apache.flume.sink.kafka.KafkaSink
exec-memory-kafka.sinks.kafka-sink.brokerList = ricky:9092
exec-memory-kafka.sinks.kafka-sink.topic = streamingtopic
exec-memory-kafka.sinks.kafka-sink.batchSize = 5
exec-memory-kafka.sinks.kafka-sink.requiredAcks = 1

exec-memory-kafka.sources.exec-source.channels = memory-channel
exec-memory-kafka.sinks.kafka-sink.channel = memory-channel

10. 启动消费者
kafka-console-consumer.sh --zookeeper ricky:2181 --topic streamingtopic

11. 启动flume
flume-ng agent \
--name exec-memory-kafka \
--conf $FLUME_HOME/conf \
--conf-file /home/ricky/app/flume-1.6.0-cdh5.7.0/conf/streaming_project2.conf \
-Dflume.root.logger=INFO,console

12. 编写代码 ImoocStatStreamingApp

13. 编写代码 DateUtils

14. 编写代码 ClickLog

功能 统计今天到现在为止实战课程的访问量
yyyyMMdd courseid

使用数据库来进行存储统计结果 (Spark Streaming把统计结果写入到数据库里面)
可视化前端根据 yyyyMMdd courseid 把数据库里面的统计结果展示出来

选择什么数据库作为统计结果的存储
RDBMS: MySQL
day       course_id  click_count
20171111  1          10
20171111  2          10

下一个批次数据进来以后
20171111 + 1 ===> click_count + 下一个批次的统计结果 ===> 写入到数据库中

NoSQL HBase Redis
HBase 一个API 非常方便
20171111 + 1 ===> click_count + 下一个批次的统计结果

启动 Hadoop
cd $HADOOP_HOME
sbin/start-dfs.sh

启动 Hbase
cd $HBASE_HOME
bin/hbase.sh
启动 Hbase 客户端
bin/hbase shell

HBase表设计
创建表 create 'course_clickcount', 'info'
Rowkey设计
day_courseid

编写代码 CourseClickCount

编写代码 CourseClickCountDAO

16. 编写代码 HBaseUtils

功能 统计今天到现在为止从搜索引擎引流过来的实战课程的访问量
HBase 表设计
create 'course_search_clickcount','info'
rowKey设计 20171111 + search + 1

编写代码 CourseSearchClickCount