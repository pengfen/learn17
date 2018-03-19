package spark.sql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
;

/**
  * SQLContext的使用:
  * 注意：IDEA是在本地，而测试数据是在服务器上 ，能不能在本地进行开发测试的？
  *
  * 1. 编写代码
  *
  * 2. 本地测试
  * Program argumets: file:///home/ricky/data/spark/sql/people.txt
  * 控制台查看结果
  *
  * 入参 /home/ricky/data/spark/sql/people.txt
  * 控制台查看结果
  *
  * hdfs://ricky:9000/people.txt
  * hadoop fs -put people.txt /
  * 控制台查看结果
  *
  * 3. 打包
  * mvn clean package -DskipTests
  *
  * 4. 上传jar包文件至服务器
  * ricky@ricky:~$ cd IdeaProjects/spark-learn/target/
  * ricky@ricky:~/IdeaProjects/spark-learn/target$ cp spark-learn-1.0.jar ~/spark-jar/
  *
  * 5. 正式环境运行
  * bin/spark-submit --class com.spark_sql.SQLContextApp --executor-memory 512m --total-executor-cores 2 /home/ricky/spark-jar/spark-learn-1.0.jar
  * hdfs://ricky:9000/people.txt
  *
  * bin/spark-submit --class com.spark_sql.SQLContextApp /home/ricky/spark-jar/spark-learn-1.0.jar
  *
  * bin/spark-submit \
  * --class com.spark_sql.SQLContextApp \
  * --master local[2] \
  * /home/ricky/spark-jar/spark-learn-1.0.jar \
  * hdfs://ricky:9000/people.txt
  *
  * 6. 编写脚本 sql_context.sh
  */
object SQLContextApp {

  def main(args: Array[String]) {
    if (args.length != 1) {
      // file:///home/ricky/data/people.json
      System.err.println("Usage: <SQLContextApp> in_path")
      System.exit(1)
    }

    val path = args(0)

    //val path = "file:///home/ricky/data/spark/sql/people.txt"
    //val path = "/home/ricky/data/spark/sql/people.txt"
    //val path = "hdfs://ricky:9000/people.txt"

    //1)创建相应的Contextjm
    val sparkConf = new SparkConf()

    //在测试或者生产中，AppName和Master我们是通过脚本进行指定
    //sparkConf.setAppName("SQLContextApp").setMaster("local[2]")

    val sc = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sc)

    //2)相关的处理: json
    val people = sqlContext.read.format("json").load(path)
    people.printSchema()
//    root
//    |-- _corrupt_record: string (nullable = true)
    people.show()
//    +---------------+
//    |_corrupt_record|
//    +---------------+
//    |  1,caopeng1,18|
//      |  2,caopeng2,20|
//      |  3,caopeng3,30|
//      |  4,caopeng4,40|
//      |  5,caopeng5,50|
//      +---------------+

    //people.toDF(); --> 数据入库

    //3)关闭资源
    sc.stop()
  }

}
