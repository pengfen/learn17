package spark.sql

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}
;

/**
  * HiveContext的使用
  * 使用时需要通过--jars 把mysql的驱动传递到classpath
  *
  * 1. 编写代码
  *
  * 2. 创建emp表
  * create table emp(empno int, ename string, job string, mgr int, hiredate string, sal double, comm double, deptno int
  ) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';
  * load data local inpath '/home/hadoop/data/emp.txt' into table emp;
  *
  * cp hive-site.xml ~/app/spark-2.1.0-bin-2.6.0-cdh5.7.0/conf/
  *
  * 3. 本地运行会出现错误
  * Table or view 'emp' not found in database 'default';
  *
  * 3. 打包 Maven Projects ---> Lifecycle ---> package
  * 注意 打包前将setMaster()注释掉
  * 使用命令打包 cd IdeaProjects/spark-learn ---> mvn clean package -DskipTests
  * 打包后的文件 /home/ricky/IdeaProjects/spark-learn/target/spark-learn-1.0.jar
  *
  * 4. 上传jar包文件至服务器
  * ricky@ricky:~$ cd IdeaProjects/spark-learn/target/
  * ricky@ricky:~/IdeaProjects/spark-learn/target$ cp spark-learn-1.0.jar ~/spark-jar/
  *
  * 5. 服务器上运行
  * cd $SPARK_HOME
  * bin/spark-submit --class com.spark_sql.HiveContextApp --master local[2] --jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar
  * --executor-memory 512m --total-executor-cores 2 /home/ricky/spark-jar/spark-learn-1.0.jar
  *
  * spark-submit --class com.spark_sql.HiveContextApp --master local[2] --jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar
  * /home/ricky/spark-jar/spark-learn-1.0.jar
  *
  * 6. 编写脚本 hive_context.sh
  *
  */
object HiveContextApp {

  def main(args: Array[String]) {
    //1)创建相应的Context
    val sparkConf = new SparkConf()

    //在测试或者生产中，AppName和Master我们是通过脚本进行指定
    // cp sparkConf.setAppName("HiveContextApp").setMaster("local[2]")

    val sc = new SparkContext(sparkConf)
    val hiveContext = new HiveContext(sc)

    //2)相关的处理:
    //hiveContext.table("spark_learn.emp").show
    hiveContext.table("emp").show
//    +-----+------+---------+----+----------+-------+------+------+
//    |empno| ename|      job| mgr|  hiredate|    sal|  comm|deptno|
//    +-----+------+---------+----+----------+-------+------+------+
//    | 7369| SMITH|    CLERK|7902|1980-12-17|  800.0|  null|    20|
//      | 7499| ALLEN| SALESMAN|7698| 1981-2-20| 1600.0| 300.0|    30|
//      | 7521|  WARD| SALESMAN|7698| 1981-2-22| 1250.0| 500.0|    30|
//      | 7566| JONES|  MANAGER|7839|  1981-4-2| 2975.0|  null|    20|
//      | 7654|MARTIN| SALESMAN|7698| 1981-9-28| 1250.0|1400.0|    30|
//      | 7698| BLAKE|  MANAGER|7839|  1981-5-1| 2850.0|  null|    30|
//      | 7782| CLARK|  MANAGER|7839|  1981-6-9| 2450.0|  null|    10|
//      | 7788| SCOTT|  ANALYST|7566| 1987-4-19| 3000.0|  null|    20|
//      | 7839|  KING|PRESIDENT|null|1981-11-17| 5000.0|  null|    10|
//      | 7844|TURNER| SALESMAN|7698|  1981-9-8| 1500.0|   0.0|    30|
//      | 7876| ADAMS|    CLERK|7788| 1987-5-23| 1100.0|  null|    20|
//      | 7900| JAMES|    CLERK|7698| 1981-12-3|  950.0|  null|    30|
//      | 7902|  FORD|  ANALYST|7566| 1981-12-3| 3000.0|  null|    20|
//      | 7934|MILLER|    CLERK|7782| 1982-1-23| 1300.0|  null|    10|
//      | 8888|  HIVE|  PROGRAM|7839| 1988-1-23|10300.0|  null|  null|
//      | 7369| SMITH|    CLERK|7902|1980-12-17|  800.0|  null|    20|
//      | 7499| ALLEN| SALESMAN|7698| 1981-2-20| 1600.0| 300.0|    30|
//      | 7521|  WARD| SALESMAN|7698| 1981-2-22| 1250.0| 500.0|    30|
//      | 7566| JONES|  MANAGER|7839|  1981-4-2| 2975.0|  null|    20|
//      | 7654|MARTIN| SALESMAN|7698| 1981-9-28| 1250.0|1400.0|    30|
//      +-----+------+---------+----+----------+-------+------+------+

    //hiveContext.table("emp").toDF() ---> 数据入库

    //3)关闭资源
    sc.stop()
  }

}
