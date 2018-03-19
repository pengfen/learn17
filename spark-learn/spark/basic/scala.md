scala 的安装

1. 下载scala
[官网下载](http://www.scala-lang.org/download/2.11.8.html)
[百度云下载](https://pan.baidu.com/s/1o92QyHs)

2. 解压安装scala
tar -zxvf scala-2.11.8.tgz -C ~/app/

3. 配置环境变量
vi .bashrc (centos vi /etc/profile .bash_profile)
export SCALA_HOME=/home/ricky/app/scala-2.11.8
export PATH=$PATH:$SCALA_HOME/bin

4. 检测
scala

5. 删除无用命令
删除所有 *.bat ---> cd bin ---> rm -rf *.bat