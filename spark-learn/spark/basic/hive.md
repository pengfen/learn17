Hive环境

1. Hive 下载
[官网下载](http://archive.cloudera.com/cdh5/chd/5/)
wget http://archive.cloudera.com/cdh5/chd/5/hive-1.1.0-cdh5.7.0.tar.gz
[百度云下载]()

2. 解压安装
tar -zxvf hive-1.1.0-cdh5.7.0.tar.gz -C ~/app/

3. 配置环境变量
vi .bashrc
export HIVE_HOME=/home/ricky/app/hive-1.1.0-cdh5.7.0
export PATH=$PATH:$HIVE_HOME/bin
source .bashrc

4. 配置文件
cd $HIVE_HOME/conf
cp hive-env.sh.template hive-env.sh
vi hive-env.sh
HADOOP_HOME=/home/ricky/app/hadoop-2.6.0-cdh5.7.0

vi hive-site.xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
    <property>
        <name>javax.jdo.option.ConnectionURL</name>
        <value>jdbc:mysql://localhost:3306/hive?createDatabaseIfNotExist=true</value>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionDriverName</name>
        <value>com.mysql.jdbc.Driver</value>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionUsername</name>
        <value>root</value>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionPassword</name>
        <value>123456</value>
    </property>
    <!--
    <property>
        <name>kite.hive.allow-local-metastore</name>
        <value>true</value>
    </property>
    <property>
        <name>hive.metastore.uris</name>
        <value>thrift://localhost:9083</value>
    </property>
    -->
</configuration>

5. 复制mysql驱动包
cp /home/ricky/software/mysql-connector-java-5.1.27-bin.jar lib/

(删除所有 *.cmd ---> cd bin ---> rm -rf *.cmd)