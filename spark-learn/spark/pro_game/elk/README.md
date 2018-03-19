1. logstash.html logstash 安装

grok.html

filter.html

base.conf

logstash.conf

2. 启动kafka

3. 编写配置文件 flow-es.conf (logstash ---> elasticsearch)

4. 编写配置文件 flow-kafka.conf (logstash ---> kafka)

/home/ricky/app/logstash-6.2.2/bin/logstash agent -f /home/ricky/app/logstash-6.2.2/conf/flow-kafka.conf

gs-kafka.conf

es.html

http://www.cnblogs.com/chowmin/articles/4629220.html

es配置 elasticsearch.yml
cluster.name: ricky
node.name: ricky
path.data: ~/app/elasticsearch/data
path.logs: ~/app/elasticsearch/logs
network.host: 127.0.0.1

kibana配置
elasticsearch.url: "http://172.16.0.14:9200"

编写配置文件 flow-kafka-es.conf
/home/ricky/app/logstash-6.2.2/bin/logstash agent -f /home/ricky/app/logstash-6.2.2/conf/flow-kafka-es.conf

kafka-es.conf (解决乱码)


kafka server.properties hostname问题
https://discuss.elastic.co/t/logstash-kafka-output-plugins-not-working-on-windows/25253

"\t"问题
https://github.com/elastic/logstash/issues/1645

grok
/bigdata/logstash-2.3.1/vendor/bundle/jruby/1.9/gems/logstash-patterns-core-2.0.5/patterns