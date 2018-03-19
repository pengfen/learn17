package spark.data

import java.util.Properties

import org.apache.spark.sql.SparkSession
;

/**
  * 使用外部数据源综合查询Hive和MySQL的表数据
  *
  * spark 操作 hive 表数据
  * spark.table(tableName)
  * df.write.saveAsTable(tableName)
  *
  * spark-shell --master local[2] --jars ~/software/mysql-connector-java-5.1.27-bin.jar
  *
  * spark.sql("show tables").show 显示所有hive表
  * spark.table("emp").show 显示员工表
  * spark.sql("select deptno, count(1) from emp group by deptno").show
  * spark.sql("select deptno, count(1) from emp group by deptno").filter("deptno is not null").show
  * spark.sql("select deptno, count(1) as count from emp where group by deptno").filter("deptno is not null").write.saveAsTable("spark_hive_test")
  * spark.table("spark_hive_test").show
  *
  * 在生產环境中一定要注意设置spark.sql.shuffle.partitions 默认200
  * spark.sqlContext.setConf("spark.sql.shuffle.partitions", "10") 设置分区 默认是200
  *
  * spark 操作mysql表数据
  * val jdbcDF = spark.read.format("jdbc").option("url", "jdbc:postgresql:dbserver").option("dbtable", "schema.tablename").option("user", "username").option("password", "password").load()
  * scala> val jdbcDF = spark.read.format("jdbc").option("url", "jdbc:mysql://localhost:3306/spark_learn").option("driver", "com.mysql.jdbc.Driver").option("dbtable", "spark_learn.person").option("user", "root").option("password", "123456").load()
jdbcDF: org.apache.spark.sql.DataFrame = [id: bigint, name: string ... 1 more field]

scala> jdbcDF.printSchema
root
 |-- id: long (nullable = false)
 |-- name: string (nullable = false)
 |-- age: integer (nullable = false)
  *
  * jdbcDF.show 显示表数据
  * jdbcDF.select("id", "name").show
  *
create database spark_learn;
use spark_learn;

CREATE TABLE DEPT(
DEPTNO int(2) PRIMARY KEY,
DNAME VARCHAR(14) ,
LOC VARCHAR(13) ) ;

INSERT INTO DEPT VALUES(10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT VALUES(20,'RESEARCH','DALLAS');
INSERT INTO DEPT VALUES(30,'SALES','CHICAGO');
INSERT INTO DEPT VALUES(40,'OPERATIONS','BOSTON');
  *
  * 交互式测试
  * spark-shell --master local[2] --jars ~/software/mysql-connector-java-5.1.27-bin.jar
  */
object HiveMySQLApp {

  def main(args: Array[String]) {
    // spark 集群的入口 ---> spark2以前
    //    val conf = new SparkConf()
    //
    //    // A master URL must be set in your configuration 本地测试时如果没有配置master会出现此错误
    //    conf.setAppName("WordCount").setMaster("local[2]")
    //
    //    val sc = new SparkContext(conf)

    // spark2
    val spark = SparkSession.builder().appName("HiveMySQLApp")
      .master("local[2]").getOrCreate()

    // 加载Hive表数据
    val hiveDF = spark.table("emp")

    // 加载MySQL表数据
    // 1. 使用 option 加载
    // val mysqlDF = spark.read.format("jdbc").option("url", "jdbc:mysql://localhost:3306").option("dbtable", "spark.DEPT")
    // .option("user", "root").option("password", "123456").option("driver", "com.mysql.jdbc.Driver").load()
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")
    prop.put("driver", "com.mysql.jdbc.Driver")
    val mysqlDF = spark.read.jdbc("jdbc:mysql://localhost:3306", "spark_learn.DEPT", prop)

    // JOIN
    val resultDF = hiveDF.join(mysqlDF, hiveDF.col("deptno") === mysqlDF.col("DEPTNO"))
    resultDF.show

    resultDF.select(hiveDF.col("empno"),hiveDF.col("ename"),
      mysqlDF.col("deptno"), mysqlDF.col("dname")).show

    spark.stop()
  }

}
