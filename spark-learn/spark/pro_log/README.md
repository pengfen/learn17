1. intro.html 介绍

2. 编写脚本generate.py (generate.php)

3. 编写定时任务每分钟产生五百条
*/1 * * * *  /usr/bin/php /home/ricky/script/spark/pro/log_generate.php

4. 启动zk
cd $ZK_HOME
bin/zkServer.sh start

5. 启动kafka (script/kafka.sh)
bin/kafka-server-start.sh -daemon /home/ricky/app/kafka_2.11-0.9.0.0/config/server.properties

6. 通过list命令查看创建的topic
bin/kafka-topics.sh --list --zookeeper localhost:2181 

7. 创建topic (创建一个log_topic 它只有一个分区 一个副本)
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic log_topic

8. 启动消费者(kafka consumer)
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic log_topic --from-beginning

9. 编写flume配置文件 (log.conf)

10. 启动flume
bin/flume-ng agent --conf conf --conf-file conf/log.conf --name log -Dflume.root.logger=INFO,console

11. 编写StatLogApp