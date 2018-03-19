1. 编写配置文件
streaming.conf

agent1.sources=avro-source
agent1.channels=logger-channel
agent1.sinks=log-sink

#define source
agent1.sources.avro-source.type=avro
agent1.sources.avro-source.bind=0.0.0.0
agent1.sources.avro-source.port=41414

#define channel
agent1.channels.logger-channel.type=memory

#define sink
agent1.sinks.log-sink.type=logger

agent1.sources.avro-source.channels=logger-channel
agent1.sinks.log-sink.channel=logger-channel

2. 运行
flume-ng agent \
--conf $FLUME_HOME/conf \
--conf-file $FLUME_HOME/conf/streaming.conf \
--name agent1 \
-Dflume.root.logger=INFO,console

3. test 目录下创建 java 目录
File ---> Project Structure ---> Modules ---> 选择java目录 ---> 设为 Tests

4. 编写代码 LoggerGenerator

import org.apache.log4j.Logger;

/**
 * 模拟日志产生
 */
public class LoggerGenerator {

    private static Logger logger = Logger.getLogger(LoggerGenerator.class.getName());

    public static void main(String[] args) throws Exception {

        int index = 0;

        while (true) {
            Thread.sleep(1000);
            logger.info("current value:" + index ++);
        }
    }
}

5. test 目录下创建 resources 目录
File ---> Project Structure ---> Modules ---> 选择resources目录 ---> 设为 Test Resources

6. 编写 properties (log4j.properties)
log4j.rootLogger=INFO,stdout,flume

log4j.appender.stdout = org.apache.log4j.ConsoleAppender
log4j.appender.stdout.target = System.out
log4j.appender.stdout.layout = org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} [%t] [%c] [%p] - %m%n

#log4j向flume中输入
log4j.appender.flume = org.apache.flume.clients.log4jappender.Log4jAppender
log4j.appender.flume.Hostname = ricky
log4j.appender.flume.Port = 41414
log4j.appender.flume.UnsafeMode = true

7. 添加依赖
<dependency>
<groupId>org.apache.flume.flume-ng-clients</groupId>
<artifactId>flume-ng-log4jappender</artifactId>
<version>1.6.0</version>
</dependency>

8. 运行代码

9. 查看控制台
2018-01-30 22:39:30,904 [main] [org.apache.flume.api.NettyAvroRpcClient] [WARN] - Using default maxIOWorkers
2018-01-30 22:39:32,189 [main] [LoggerGenerator] [INFO] - current value:0
2018-01-30 22:39:33,264 [main] [LoggerGenerator] [INFO] - current value:1

10. 查看flume控制台

将收集的日志输出到kafka
1. 启动zookeeper
bin/zkServer.sh start

2. 启动kafka
bin/kafka-server-start.sh -daemon /home/ricky/app/kafka_2.11-0.9.0.0/config/server.properties

3. 查看topic
bin/kafka-topics.sh --list --zookeeper ricky:2181

4. 创建topic
bin/kafka-topics.sh --create --zookeeper ricky:2181 --replication-factor 1 --partitions 1 --topic streamingtopic

5. 编写配置
streaming2.conf

agent1.sources=avro-source
agent1.channels=logger-channel
agent1.sinks=kafka-sink

#define source
agent1.sources.avro-source.type=avro
agent1.sources.avro-source.bind=0.0.0.0
agent1.sources.avro-source.port=41414

#define channel
agent1.channels.logger-channel.type=memory

#define sink
agent1.sinks.kafka-sink.type=org.apache.flume.sink.kafka.KafkaSink
agent1.sinks.kafka-sink.topic = streamingtopic
agent1.sinks.kafka-sink.brokerList = ricky:9092
agent1.sinks.kafka-sink.requiredAcks = 1
agent1.sinks.kafka-sink.batchSize = 20

agent1.sources.avro-source.channels=logger-channel
agent1.sinks.kafka-sink.channel=logger-channel

6. 运行flume
flume-ng agent \
--conf $FLUME_HOME/conf \
--conf-file $FLUME_HOME/conf/streaming2.conf \
--name agent1 \
-Dflume.root.logger=INFO,console

7. 启动kafka 消费者
bin/kafka-console-consumer.sh --zookeeper ricky:2181 --topic streamingtopic

8. 运行代码 KafkaStreamingApp

9. 查看控制台
2018-01-30 23:09:22,484 [main] [LoggerGenerator] [INFO] - current value:1
2018-01-30 23:09:23,486 [main] [LoggerGenerator] [INFO] - current value:2

10. 查看消费者控制台
