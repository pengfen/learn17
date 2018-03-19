日志分析项目

需求一 统计网站最受欢迎的课程/手记的Top N访问次数

1. 编写代码 SparkStatFormatJob

2. 编写代码 DateUtils

3. 编写代码 AccessConvertUtil

4. 编写代码 SparkStatCleanJob

5. 编写代码 IpUtils
ricky@ricky:~/software$ git clone https://github.com/wzhe06/ipdatabase.git
正克隆到 'ipdatabase'...
remote: Counting objects: 59, done.
remote: Total 59 (delta 0), reused 0 (delta 0), pack-reused 59
展开对象中: 100% (59/59), 完成.
ricky@ricky:~/software$ cd ipdatabase/
ricky@ricky:~/software/ipdatabase$ mvn clean package -DskipTests

6. 安装 ipdatabase
mvn install:install-file -Dfile=/home/ricky/software/ipdatabase/target/ipdatabase-1.0-SNAPSHOT.jar \
-DgroupId=com.ggstar \
-DartifactId=ipdatabase \
-Dversion=1.0 \
-Dpackaging=jar

7. 添加依赖
<dependency>
<groupId>com.ggstar</groupId>
<artifactId>ipdatabase</artifactId>
<version>1.0</version>
</dependency>

<dependency>
<groupId>org.apache.poi</groupId>
<artifactId>poi-ooxml</artifactId>
<version>3.14</version>
</dependency>

<dependency>
<groupId>org.apache.poi</groupId>
<artifactId>poi</artifactId>
<version>3.14</version>
</dependency>

按照需求完成统计信息并将统计结果入库
8. 编写代码 TopNStatJob

9. 编写代码 MySQLUtils

10. 编写代码 DayVideoAccessStat

11. 编写代码 StatDAO

需求二 按地市统计网站最受欢迎的Top N课程
12. 编写代码 DayCityVideoAccessStat

需求三 按流量统计网站最受欢迎的Top N课程
13. 编写代码 DayVideoTrafficsStat

改造在yarn上运行
14. 编写代码 SparkStatCleanJobYARN
打包 (注意 pom.xml中需要添加plugin 查看一下内存使用free)
<plugin>
<artifactId>maven-assembly-plugin</artifactId>
<configuration>
  <archive>
    <manifest>
      <mainClass></mainClass>
    </manifest>
  </archive>
  <descriptorRefs>
    <descriptorRef>jar-with-dependencies</descriptorRef>
  </descriptorRefs>
</configuration>
</plugin>
mvn assembly:assembly

上传jar文件至服务器
cp target/spark-learn-1.0-jar-with-dependencies.jar ~/spark-jar/

hadoop fs -mkdir -p /spark_sql/input
hadoop fs -put access.log /spark_sql/input
编写stat_clean.sh

运行后查看结果 hadoop fs -ls /spark_sql/clean
hadoop fs -ls /spark_sql/clean/day=20170511

spark-shell --master local[2] --jars ~/software/mysql-connector-java-5.1.27-bin.jar
scala> spark.read.format("parquet").load("hdfs://ricky:9000/spark_sql/clean/day=20170511/part-00000-8a01791b-dc80-430f-91de-01e8542ca258.c000.snappy.parquet").show(false)
+----------------------------------+-------+-----+-------+---------------+----+-------------------+
|url                               |cmsType|cmsId|traffic|ip             |city|time               |
+----------------------------------+-------+-----+-------+---------------+----+-------------------+
|http://www.imooc.com/video/4500   |video  |4500 |304    |218.75.35.226  |未知  |2017-05-11 14:09:14|
|http://www.imooc.com/video/14623  |video  |14623|69     |202.96.134.133 |未知  |2017-05-11 15:25:05|
|http://www.imooc.com/article/17894|article|17894|115    |202.96.134.133 |未知  |2017-05-11 07:50:01|
|http://www.imooc.com/article/17896|article|17896|804    |218.75.35.226  |未知  |2017-05-11 02:46:43|

处理城市
上传资源文件至服务器
cp ipDatabase.csv ~/spark-jar/
cp ipRegion.xlsx ~/spark-jar/
scala> spark.read.format("parquet").load("hdfs://ricky:9000/spark_sql/clean/day=20170511/part-00000-88083103-69f4-4901-b030-821fddacc0fc.c000.snappy.parquet").show(false)
+----------------------------------+-------+-----+-------+---------------+----+-------------------+
|url                               |cmsType|cmsId|traffic|ip             |city|time               |
+----------------------------------+-------+-----+-------+---------------+----+-------------------+
|http://www.imooc.com/video/4500   |video  |4500 |304    |218.75.35.226  |浙江省 |2017-05-11 14:09:14|
|http://www.imooc.com/video/14623  |video  |14623|69     |202.96.134.133 |广东省 |2017-05-11 15:25:05|
|http://www.imooc.com/article/17894|article|17894|115    |202.96.134.133 |广东省 |2017-05-11 07:50:01|

15. 编写代码 TopNStatJobYARN
打包 mvn assembly:assembly
编写 top_n.sh

flow.html 项目运行

viedo.html java项目显示视图

flow2.html 项目升级版