下载
[jdk7](https://pan.baidu.com/s/1cTwlHW)
[jdk8](https://pan.baidu.com/s/1ggmiQhd)

1.切换到root用户：
su – root

2.查看以前是不是安装了openjdk：    
命令：rpm -qa | grep java    
显示如下：（有则卸载，没有就不用）    
tzdata-java-2013g-1.el6.noarch    
java-1.7.0-openjdk-1.7.0.45-2.4.3.3.el6.x86_64    
java-1.6.0-openjdk-1.6.0.0-1.66.1.13.0.el6.x86_64    

3.卸载openjdk：    
（其中参数“tzdata-java-2013g-1.el6.noarch”为上面查看中显示的结果，粘进来就行）    
rpm -e --nodeps tzdata-java-2013g-1.el6.noarch    
rpm -e --nodeps java-1.7.0-openjdk-1.7.0.45-2.4.3.3.el6.x86_64    
rpm -e --nodeps java-1.6.0-openjdk-1.6.0.0-1.66.1.13.0.el6.x86_64    

4.上传jdk-7u45-linux-x64.tar.gz到Linux上 (安装rz命令)

5.解压jdk到~/app/目录或/usr/local目录    
tar -zxvf jdk-7u45-linux-x64.tar.gz -C ~/app/    
[hadoop@apeng1 download]$ sudo tar -zxvf jdk-7u45-linux-x64.tar.gz -C /usr/local/ #使用hadoop用户

6.设置环境变量，在/etc/profile文件最后追加相关内容    
vi /etc/profile (centos) .bashrc (ubuntu)    
export JAVA_HOME=/usr/local/jdk1.7.0_45    
export CLASSPATH=$JAVA_HOME/lib    
export PATH=$PATH:$JAVA_HOME/bin

7.刷新环境变量    
source /etc/profile

8.测试java命令是否可用    
java -version

9.使用脚本安装JDK
#!/bin/bash

BASE_SERVER=172.16.203.100 #相关安装包(服务器)    
yum install -y wget #安装wget命令    
wget $BASE_SERVER/soft/jdk-7u45-linux-x64.tar.gz #从服务器拉取jdk安装包    
tar -zxvf jdk-7u45-linux-x64.tar.gz -C /usr/local #解压    
cat >> /etc/profile << EOF    
export JAVA_HOME=/usr/local/jdk1.7.0_45    
export PATH=\$PATH:\$JAVA_HOME/bin    
EOF

直接复制安装第二 ... 台    
scp -r /usr/local/jdk1.7.0_45/ root@apeng2:/usr/local/jdk1.7.0_45    
scp -r /etc/profile root@apeng2:/etc/

登录第二台
source /etc/profile
