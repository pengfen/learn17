package spark.sql

import java.sql.DriverManager
;

/**
  * 通过JDBC的方式访问
  *
  * 添加依赖 (hive jdbc 依赖)
  * <dependency>
      <groupId>org.apache.hive</groupId>
      <artifactId>hive-jdbc</artifactId>
      <version>1.2.1</version>
    </dependency>
  *
  * 1. 编写代码
  *
  * 2. 启动服务
  * cd $SPARK_HOME
  * sbin/start-thriftserver.sh --master local[2] --jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar --hiveconf hive.server2.thrift.port=14000
  *
sbin/start-thriftserver.sh \
--master local[2] \
--jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar \
--hiveconf hive.server2.thrift.port=14000

  * 3. 运行代码
  *
  */
object SparkSQLThriftServerApp {

  def main(args: Array[String]) {

//    if (args.length != 3) {
//      // jdbc:hive2://ricky:14000 ricky
//      System.err.println("Usage: <SparkSQLThriftServerApp> url username password")
//      System.exit(1)
//    }

    Class.forName("org.apache.hive.jdbc.HiveDriver")

    val conn = DriverManager.getConnection("jdbc:hive2://ricky:14000","ricky","")
    val pstmt = conn.prepareStatement("select empno, ename, sal from emp")
    val rs = pstmt.executeQuery()
    while (rs.next()) {
      println("empno:" + rs.getInt("empno") +
        " , ename:" + rs.getString("ename") +
        " , sal:" + rs.getDouble("sal"))

    }
//    empno:7369 , ename:SMITH , sal:800.0
//    empno:7499 , ename:ALLEN , sal:1600.0
//    empno:7521 , ename:WARD , sal:1250.0
//    empno:7566 , ename:JONES , sal:2975.0
//    empno:7654 , ename:MARTIN , sal:1250.0
//    empno:7698 , ename:BLAKE , sal:2850.0
//    empno:7782 , ename:CLARK , sal:2450.0
//    empno:7788 , ename:SCOTT , sal:3000.0
//    empno:7839 , ename:KING , sal:5000.0
//    empno:7844 , ename:TURNER , sal:1500.0
//    empno:7876 , ename:ADAMS , sal:1100.0
//    empno:7900 , ename:JAMES , sal:950.0
//    empno:7902 , ename:FORD , sal:3000.0
//    empno:7934 , ename:MILLER , sal:1300.0
//    empno:8888 , ename:HIVE , sal:10300.0
//    empno:7369 , ename:SMITH , sal:800.0
//    empno:7499 , ename:ALLEN , sal:1600.0
//    empno:7521 , ename:WARD , sal:1250.0
//    empno:7566 , ename:JONES , sal:2975.0
//    empno:7654 , ename:MARTIN , sal:1250.0
//    empno:7698 , ename:BLAKE , sal:2850.0
//    empno:7782 , ename:CLARK , sal:2450.0
//    empno:7788 , ename:SCOTT , sal:3000.0
//    empno:7839 , ename:KING , sal:5000.0
//    empno:7844 , ename:TURNER , sal:1500.0
//    empno:7876 , ename:ADAMS , sal:1100.0
//    empno:7900 , ename:JAMES , sal:950.0
//    empno:7902 , ename:FORD , sal:3000.0
//    empno:7934 , ename:MILLER , sal:1300.0
//    empno:8888 , ename:HIVE , sal:10300.0

    rs.close()
    pstmt.close()
    conn.close()

  }

}
