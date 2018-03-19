1. 下载tomcat
[tomcat7](https://pan.baidu.com/s/1o98qP4Y)

2. 上传apache-tomcat-7.0.68.tar.gz到Linux上

3. 解压tomcat
tar -zxvf apache-tomcat-7.0.68.tar.gz -C ~/app/

4. 启动tomcat
~/app/apache-tomcat-7.0.68/bin/startup.sh

5. 查看tomcat进程是否启动
jps

6. 查看tomcat进程端口
netstat -anpt | grep 2465

7. 通过浏览器访问tomcat
http://192.168.0.101:8080/

tomcat (删除所有 *.cmd ---> cd bin ---> rm -rf *.cmd)