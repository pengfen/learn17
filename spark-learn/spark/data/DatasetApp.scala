package spark.data

import org.apache.spark.sql.SparkSession
;

/**
  * Dataset操作
  *
  * 1. 编写代码
  *
  * hadoop fs -put sales.csv /
  *
  * 2. 测试
  */
object DatasetApp {

  def main(args: Array[String]) {
    // spark 集群的入口 ---> spark2以前
    //    val conf = new SparkConf()
    //
    //    // A master URL must be set in your configuration 本地测试时如果没有配置master会出现此错误
    //    conf.setAppName("WordCount").setMaster("local[2]")
    //
    //    val sc = new SparkContext(conf)

    // spark2
    val spark = SparkSession.builder().appName("DatasetApp")
      .master("local[2]").getOrCreate()

    //注意：需要导入隐式转换
    import spark.implicits._

    // val path = "file:///home/ricky/data/spark/data/sales.csv"
    // val path = "/home/ricky/data/spark/data/sales.csv"
    val path = "hdfs://ricky:9000/sales.csv"

    //spark如何解析csv文件？
    val df = spark.read.option("header","true").option("inferSchema","true").csv(path)
    df.show(2)
//    +-----+
//    |value|
//    +-----+
//    |    1|
//      |    2|
//      +-----+

    // dataFrame ---> dataSet
    val ds = df.as[Sales]
    ds.map(line => line.itemId).show(2)

    ds.createOrReplaceTempView("sales") // 对应实体类 Sales
    spark.sql("select customerId from sales").show(2)

    //df.seletc("name")
    //df.select("name").show(2)

    ds.map(line => line.itemId)

    spark.stop()
  }

  case class Sales(transactionId:Int,customerId:Int,itemId:Int,amountPaid:Double)
}
