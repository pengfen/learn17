maven 安装

1. 下载
[官网下载](maven.apache.org/download.cgi)
[百度云下载](https://pan.baidu.com/s/1dFRUkYd)

2. 解压安装
tar -zxvf apache-maven-3.3.9-bin.tar.gz -C ~/app/

3. 配置环境变量     
vi .bashrc (centos vi /etc/profile .bash_profile)    
export MAVEN_HOME=/home/ricky/app/maven-3.3.9    
export PATH=$PATH:$MAVEN_HOME/bin    

4. 配置本地库    
cd $MAVEN_HOME    
cd conf    
vi settings.xml    
<localRepository>/home/ricky/repository</localRepository>

5. 依赖库查询    
http://mvnrepository.com/    
搜索所需依赖库 ---> 单击对应的应用 ---> 单击对应的版本 ---> 复制依赖添加到pom.xml    
<dependency>
    <groupId></groupId>
    <artifactId></artifactId>
    <version></version>
</dependency>

(删除所有 *.cmd ---> cd bin ---> rm -rf *.cmd)
6. 检测是否安装成功
mvn -v
