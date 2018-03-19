hadoop安装
1. 下载
[官网下载](hadoop.apache.org)
[cloud下载](archive.cloudera.com/cdh5/cdh/5/hadoop-2.6.0-cdh5.7.0.tar.gz)
[百度云下载](https://pan.baidu.com/s/1smLGv85)

2. 解压安装
tar -zxvf hadoop-2.6.0-cdh5.7.0.tar.gz -C ~/app/

3. 配置无密码登录
ssh-keygen (centos生成密钥)
ssh-keygen -t rsa (ubuntu生成密钥)

cd ~/.ssh
cp ~/.ssh/id_rsa.pub ~/.ssh/authorized_keys

4. 配置环境变量
vi .bashrc
export HADOOP_HOME=/home/ricky/app/hadoop-2.6.0-cdh5.7.0
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin

5. 配置 hadoop
cd $HADOOP_HOME
cd etc/hadoop

vi hadoop-env.sh
export JAVA_HOME=/home/ricky/app/jdk1.8.0_144

vi core-site.xml
<configuration>

    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://ricky:9200</value>
    </property>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/home/ricky/app/tmp</value>
    </property>

</configuration>

vi hdfs-site.xml
<configuration>

    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>

</configuration>

vi slaves
ricky

bin/hdfs namenode -format

6. 启动dfs (删除所有 *.cmd ---> cd bin ---> rm -rf *.cmd)
sbin/start-dfs.sh

7. 检测是否成功
jps

http://ricky:50070

8. 配置yarn
cp mapred-site.xml.template mapred-site.xml
vi mapred-site.xml
<configuration>

    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>

</configuration>

vi yarn-site.xml
<configuration>

    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>

</configuration>

10. 启动yarn
sbin/start-yarn.sh

11. 检测yarn是否安装成功
jps

http://ricky:8088

fs 相关命令
hadoop fs -ls /
hadoop fs -mkdir /data
hadoop fs -put core-site.xml /data/
hadoop fs -ls /data
hadoop fs -text /data/core-site.xml
hadoop fs -put test.txt /
hadoop fs -rmr /test.txt

cd $HADOOP_HOME
cd share/hadoop/mapreduce
hadoop jar hadoop-mapreduce-examples-2.6.0-cdh5.7.0.jar pi 2 3
