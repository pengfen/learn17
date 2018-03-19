package spark.data

import org.apache.spark.sql.SparkSession
;

/**
  * Schema Infer
  *
  * 1. 编写代码
  *
  * hadoop fs -put schema.json /
  */
object SchemaInferApp {

  def main(args: Array[String]) {
    // spark 集群的入口 ---> spark2以前
    //    val conf = new SparkConf()
    //
    //    // A master URL must be set in your configuration 本地测试时如果没有配置master会出现此错误
    //    conf.setAppName("WordCount").setMaster("local[2]")
    //
    //    val sc = new SparkContext(conf)

    // spark2
    val spark = SparkSession.builder().appName("SchemaInferApp").master("local[2]").getOrCreate()

    // val in = "file:///home/ricky/data/spark/data/schema.json"
    // val in = "/home/ricky/data/spark/data/schema.json"
    val in = "hdfs://ricky:9000/schema.json"
    val df = spark.read.format("json").load(in)

    df.printSchema()
//    root
//    |-- gender: string (nullable = true)
//    |-- height: long (nullable = true)
//    |-- name: string (nullable = true)
    // 如果 height 中有小数点，会自动转换成double类型

    df.show()
//    +------+------+-------+
//    |gender|height|   name|
//    +------+------+-------+
//    |     M|   170|  ricky|
//      |     M|   175|caopeng|
//      |     F|   165|   peng|
//      +------+------+-------+

    spark.stop()
  }

}
