hbase 安装
1. 下载
[cloud下载](archive.cloudera.com/cdh5/cdh/5/hbase-1.2.0-cdh5.7.0.tar.gz)
[百度云下载](https://pan.baidu.com/s/1nwuKjfN)

2. 解压安装
tar -zxvf hbase-1.2.0-cdh5.7.0.tar.gz -C ~/app/

3. 配置环境变量
vi .bashrc
export HBASE_HOME=/home/ricky/app/hbase-1.2.0-cdh5.7.0
export PATH=$PATH:$HBASE_HOME/bin

4. 修改相关配置文件
cd $HBASE_HOME/conf
vi hbase-env.sh
export JAVA_HOME=/home/ricky/app/jdk1.8.0_144
export HBASE_MANAGES_ZK=false # 不使用hbase而使用zookeeper来管理

vi hbase-site.xml
<configuration>

    <property>
        <name>hbase.rootdir</name>
        <value>hdfs://ricky:9200/hbase</value>
    </property>
    <property>
        <name>hbase.cluster.distributed</name>
        <value>true</value>
    </property>
    <property>
        <name>hbase.zookeeper.quorum</name>
        <value>ricky:2181</value>
    </property>

</configuration>

vi regionservers
ricky

5. 启动 zk
cd $ZK_HOME
bin/zkServer.sh start

6. 启动 hbase (删除所有 *.cmd ---> cd bin ---> rm -rf *.cmd)
cd $HBASE_HOME
bin/start-hbase.sh

7. 操作 hbase
bin/hbase shell
version
status
create 'member','info','address'
list #查看所有表
desc
describe
describe 'member' #查看表结构
scan 'member' #查看表数据

错误 Can't get master address from ZooKeeper; znode data == null
查看日志 logs/hbase-ricky-master-ricky.log
ricky:9200 ---> ricky:9000 造成的
重启zk ---> 重启hbase
