zookeeper 安装(单节点)

1. 下载
[官网下载](http://zookeeper.apache.org/)
[百度云下载](https://pan.baidu.com/s/1c3ZEbmw)

2. 解压安装
tar -zxvf zookeeper-3.4.5-cdh5.7.0 -C ~/app/

3. 配置环境变量
vi .bashrc
export ZK_HOME=/home/ricky/app/zookeeper-3.4.5-cdh5.7.0
export PATH=$PATH:$ZK_HOME/bin

4. 配置
cd $ZK_HOME
cd conf
cp zoo_sample.cfg zoo.cfg
vi zoo.cfg
dataDir=/home/ricky/app/tmp/zk

5. 启动 (删除所有 *.cmd ---> cd bin ---> rm -rf *.cmd)
cd $ZK_HOME
bin/zkServer.sh start

6. 查看是否启动成功
jsp
QuorumPeerMain
