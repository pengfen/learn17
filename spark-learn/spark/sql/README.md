数据输入源 /home/ricky/data/spark/basic
脚本 /home/ricky/script/spark/basic

1. 添加依赖
<properties>
<scala.version>2.11.0</scala.version>
<spark.version>2.1.0</spark.version>
</properties>

<dependencies>
<!-- scala 依赖 -->
<dependency>
<groupId>org.scala-lang</groupId>
<artifactId>scala-library</artifactId>
<version>${scala.version}</version>
</dependency>

<!-- sparkSQL 依赖 -->
<dependency>
<groupId>org.apache.spark</groupId>
<artifactId>spark-sql_2.11</artifactId>
<version>${spark.version}</version>
</dependency>
</dependencies>

2. 编写代码 SQLContextApp

3. 编写代码 HiveContextApp
<!-- spark hive 依赖 -->
<dependency>
  <groupId>org.apache.spark</groupId>
  <artifactId>spark-hive_2.11</artifactId>
  <version>${spark.version}</version>
  <!--
  <scope>provided</scope>
  -->
</dependency>

4. 编写代码 SparkSessionApp

5. 编写代码 SparkSQLThriftServerApp


6. intro.html spark sql介绍

7. 编写代码 SQLDemo (DataFrame介绍)

数据源 ---> data包

8. 编写代码 InferringSchema (通过反射推断Schema)
将程序打成jar包，上传到spark集群，提交Spark任务
spark-submit \
--class spark.sql.InferringSchema \
--master spark://ricky:7077 \
/home/ricky/spark-jar/spark-learn-1.0.jar \
hdfs://ricky:9000/person.txt hdfs://ricky:9000/out

查看运行结果
hdfs dfs -cat hdfs://ricky:9000/out/part-r-*

9. 编写代码 SpecifyingSchema (通过StructType直接指定Schema)
将程序打成jar包，上传到spark集群，提交Spark任务
spark-submit \
--class spark.sql.InferringSchema \
--master spark://ricky:7077 \
/home/ricky/spark-jar/spark-learn-1.0.jar \
hdfs://ricky:9000/person.txt \
hdfs://ricky:9000/out1

查看结果
hdfs dfs -cat  hdfs://node1.itcast.cn:9000/out1/part-r-*

10. 编写代码 JdbcRDD (将数据写入到MySQL中)
3.将Jar包提交到spark集群
spark-submit \
--class spark.sql.JdbcRDD \
--master spark://node1.itcast.cn:7077 \
--jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar \
--driver-class-path /home/ricky/software/mysql-connector-java-5.1.27-bin.jar \
/home/ricky/spark-jar/spark-learn-1.0.jar


spark_sql_hive.html spark sql 结合 hive