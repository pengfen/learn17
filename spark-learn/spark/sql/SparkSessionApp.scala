package spark.sql

import org.apache.spark.sql.SparkSession
;

/**
  * SparkSession的使用
  * 1. 编写代码
  *
  * 2. 本地测试 (传递参数) 运行代码
  * 输入参数使用 file:///home/ricky/data/spark/sql/people.txt
  * 查看控制台
  *
  * 入参使用 /home/ricky/data/spark/sql/people.txt
  * 查看控制台
  *
  * 入参使用 hdfs://ricky:9000/people.txt
  * 查看控制台
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
  * bin/spark-submit --class com.spark_sql.SparkSessionApp --executor-memory 512m --total-executor-cores 2 /home/ricky/spark-jar/spark-learn-1.0.jar
  * hdfs://ricky:9000/people.txt
  *
  * spark-submit --master local[2] --class com.spark_sql.SparkSessionApp /home/ricky/spark-jar/spark-learn-1.0.jar
  *
  * 6. 查看结果 hadoop fs -cat /out/part*
  *
  * 7. 编写脚本 spark_session.sh
  */
object SparkSessionApp {

  def main(args: Array[String]) {

    if (args.length != 1) {
      // file:///home/ricky/data/people.json
      System.err.println("Usage: <SparkSessionApp> in_path")
      System.exit(1)
    }

    // spark2 以前入口
    // val sparkConf = new SparkConf().setAppName("SQLContextApp").setMaster("local[2]")

    // spark2 入口
    val spark = SparkSession.builder().appName("SparkSessionApp")
      .master("local[2]").getOrCreate()

    // val in = "file:///home/ricky/data/spark/sql/people.txt"
    // val in = "/home/ricky/data/spark/sql/people.txt"
    val in = "hdfs://ricky:9000/people.txt"

    val people = spark.read.json(in)
    people.show()
//    +---------------+
//    |_corrupt_record|--c
//    +---------------+
//    |  1,caopeng1,18|
//      |  2,caopeng2,20|
//      |  3,caopeng3,30|
//      |  4,caopeng4,40|
//      |  5,caopeng5,50|
//      +---------------+

    //people.toDF() ---> 数据入库

    spark.stop()
  }
}
