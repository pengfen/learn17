package spark.data

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
;

/**
  * DataFrame和RDD的互操作
  *
  * 1. 编写 inferReflection
  *
  * 2. 测试 ---> 运行代码
  *
  * 3. 编写 program 方法
  *
  * 4. 测试 ---> 运行代码
  *
  */
object DataFrameRDDApp {

  def main(args: Array[String]) {
    // spark 集群的入口 ---> spark2以前
    //    val conf = new SparkConf()
    //
    //    // A master URL must be set in your configuration 本地测试时如果没有配置master会出现此错误
    //    conf.setAppName("WordCount").setMaster("local[2]")
    //
    //    val sc = new SparkContext(conf)

    // spark2
    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()

    // RDD ---> DataFrame 方式一
    inferReflection(spark)

    // RDD ---> DataFrame 方式二
    // program(spark)

    spark.stop()
  }

  def program(spark: SparkSession): Unit = {
    // val in = "file:///home/ricky/data/spark/sql/people.txt"
    // val in = "/home/ricky/data/spark/sql/people.txt"
    val in = "hdfs://ricky:9000/people.txt"

    // RDD ==> DataFrame
    val rdd = spark.sparkContext.textFile(in)

    val infoRDD = rdd.map(_.split(",")).map(line => Row(line(0).toInt, line(1), line(2).toInt))

    val structType = StructType(Array(StructField("id", IntegerType, true),
      StructField("name", StringType, true),
      StructField("age", IntegerType, true)))

    val infoDF = spark.createDataFrame(infoRDD,structType)
    infoDF.printSchema()
//    root
//    |-- id: integer (nullable = true)
//    |-- name: string (nullable = true)
//    |-- age: integer (nullable = true)

    infoDF.show()
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  1|caopeng1| 18|
//      |  2|caopeng2| 20|
//      |  3|caopeng3| 30|
//      |  4|caopeng4| 40|
//      |  5|caopeng5| 50|
//      +---+--------+---+

    //通过df的api进行操作
    infoDF.filter(infoDF.col("age") > 30).show
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  4|caopeng4| 40|
//      |  5|caopeng5| 50|
//      +---+--------+---+

    //通过sql的方式进行操作
    infoDF.createOrReplaceTempView("infos")
    spark.sql("select * from infos where age > 30").show()
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  4|caopeng4| 40|
//      |  5|caopeng5| 50|
//      +---+--------+---+
  }

  def inferReflection(spark: SparkSession) {
    // val in = "file:///home/ricky/data/spark/sql/people.txt"
    // val in = "/home/ricky/data/spark/sql/people.txt"
    val in = "hdfs://ricky:9000/people.txt"

    // RDD ==> DataFrame
    val rdd = spark.sparkContext.textFile(in)

    //注意：需要导入隐式转换
    import spark.implicits._
    val infoDF = rdd.map(_.split(",")).map(line => Info(line(0).toInt, line(1), line(2).toInt)).toDF()

    infoDF.show()
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  1|caopeng1| 18|
//      |  2|caopeng2| 20|
//      |  3|caopeng3| 30|
//      |  4|caopeng4| 40|
//      |  5|caopeng5| 50|
//      +---+--------+---+

    infoDF.filter(infoDF.col("age") > 30).show
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  4|caopeng4| 40|
//      |  5|caopeng5| 50|
//      +---+--------+---+

    infoDF.createOrReplaceTempView("infos")
    spark.sql("select * from infos where age > 30").show()
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  4|caopeng4| 40|
//      |  5|caopeng5| 50|
//      +---+--------+---+
  }

  case class Info(id: Int, name: String, age: Int) // 相当于 java bean

}
