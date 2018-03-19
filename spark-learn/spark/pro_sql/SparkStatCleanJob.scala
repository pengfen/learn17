package spark.pro_sql

import org.apache.spark.sql.{SaveMode, SparkSession}
import spark.pro_sql.utils.AccessConvertUtil

/**
  * 使用Spark完成我们的数据清洗操作
  *
  * 1. 编写代码
  *
  * 2. 测试
  */
object SparkStatCleanJob {

  def main(args: Array[String]) {
    val spark = SparkSession.builder().appName("SparkStatCleanJob")
      .config("spark.sql.parquet.compression.codec","gzip")
      .master("local[2]").getOrCreate()

    val in = "/home/ricky/data/spark/project/access.log";

    val accessRDD = spark.sparkContext.textFile("/home/ricky/data/access.log")

    //accessRDD.take(10).foreach(println)

    //RDD ==> DF
    val accessDF = spark.createDataFrame(accessRDD.map(x => AccessConvertUtil.parseLog(x)),
      AccessConvertUtil.struct)

    //    accessDF.printSchema()
    //    accessDF.show(false)

    accessDF.coalesce(1).write.format("parquet").mode(SaveMode.Overwrite)
      .partitionBy("day").save("/home/ricky/data/clean")

    spark.stop
  }
}
