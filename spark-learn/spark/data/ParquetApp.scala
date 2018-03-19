package spark.data

import org.apache.spark.sql.SparkSession
;

/**
  * Apache Parquet 最初的设计动机是存储嵌套式数据　比如Protocolbuffer thrift json等
  * 将这类数据存储成列式格式　以方便对其高效压缩和编码　且使用昜少的IO操作取出需要的数据
  * 这也是Parquet相比于ORC的优势　它能够透明地将Protobuf和thrift类型的数据进行列式存储
  *
  *
  * Parquet文件操作
  *
  * 1. 编写代码
  *
  * 2. 交互式测试
  * spark-shell --master local[2] --jars ~/software/mysql-connector-java-5.1.27-bin.jar
  *
  * scala> val userDF = spark.read.format("parquet").load("hdfs://ricky:9000/users.parquet")
userDF: org.apache.spark.sql.DataFrame = [name: string, favorite_color: string ... 1 more field]
  *
  *
  */
object ParquetApp {

  def main(args: Array[String]) {
    // spark 集群的入口 ---> spark2以前
    //    val conf = new SparkConf()
    //
    //    // A master URL must be set in your configuration 本地测试时如果没有配置master会出现此错误
    //    conf.setAppName("WordCount").setMaster("local[2]")
    //
    //    val sc = new SparkContext(conf)

    // spark2
    val spark = SparkSession.builder().appName("SparkSessionApp")
      .master("local[2]").getOrCreate()

    // val in = "file:///home/ricky/app/spark-2.2.0-bin-2.6.0-cdh5.7.0/examples/src/main/resources/users.parquet"
    // val in = "/home/ricky/app/spark-2.2.0-bin-2.6.0-cdh5.7.0/examples/src/main/resources/users.parquet"
    // hadoop fs -put users.parquet /
    val in = "hdfs://ricky:9000/users.parquet"

    /**
      * spark.read.format("parquet").load 这是标准写法
      */
    val userDF = spark.read.format("parquet").load(in)

    userDF.printSchema()
//    root
//    |-- name: string (nullable = true)
//    |-- favorite_color: string (nullable = true)
//    |-- favorite_numbers: array (nullable = true)
//    |    |-- element: integer (containsNull = true)

    userDF.show()
//    +------+--------------+----------------+
//    |  name|favorite_color|favorite_numbers|
//    +------+--------------+----------------+
//    |Alyssa|          null|  [3, 9, 15, 20]|
//    |   Ben|           red|              []|
//      +------+--------------+----------------+

    userDF.select("name","favorite_color").show
//    +------+--------------+
//    |  name|favorite_color|
//    +------+--------------+
//    |Alyssa|          null|
//      |   Ben|           red|
//    +------+--------------+
//
    val out = "hdfs://ricky:9000/out"
    // hadoop fs -rmr /out ---> 运行代码 ---> hadoop fs -cat /out/part*
    // {"name":"Alyssa"}{"name":"Ben","favorite_color":"red"}
    userDF.select("name","favorite_color").write.format("json").save(out)

    spark.read.load(in).show
//    +------+--------------+----------------+
//    |  name|favorite_color|favorite_numbers|
//    +------+--------------+----------------+
//    |Alyssa|          null|  [3, 9, 15, 20]|
//    |   Ben|           red|              []|

    // 会报错，因为sparksql默认处理的format就是parquet
    // spark.sparkContext.textFile(in) ---> 读取txt等日志文件
    // Could not read footer for file: FileStatus{path=hdfs://ricky:9000/people.json
    // spark.read.load("hdfs://ricky:9000/people.json").show

    spark.read.format("parquet").option("path", in).load().show
//    +------+--------------+----------------+
//    |  name|favorite_color|favorite_numbers|
//    +------+--------------+----------------+
//    |Alyssa|          null|  [3, 9, 15, 20]|
//    |   Ben|           red|              []|

    spark.stop()
  }

}
