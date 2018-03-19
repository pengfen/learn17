package spark.pro_user

import org.apache.spark.sql.SparkSession
import spark.pro_user.dao.UserDao
import spark.pro_user.domain.User

import scala.collection.mutable.ListBuffer

/**
  * 数据入库
  *
  * 数据源 /home/ricky/data/spark-out/basic
  *
  * 1. 编写代码
  *
  * 2. 在spark-learn库中创建bs_user表
  * create table bs_user(day char(8), mobile bigint(11), bs char(32), time int, x decimal(12, 6), y decimal(12, 6))
  engine=innodb default charset=utf8 comment='统计每天站点所呆时间最长的前十个用户';
  */
object DataLoad {

  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf()
//    conf.setAppName("UserLocationClean").setMaster("local[2]")
//    val sc = new SparkContext(conf)

    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()

    val in = "/home/ricky/data/spark-out/basic"
    val userRDD = spark.sparkContext.textFile(in)

    //注意：需要导入隐式转换 (注意使用sparkSession)
    import spark.implicits._
    val userDF = userRDD.map(_.split(",")).map(line => User(line(0), line(1).toLong, line(2), line(3), line(4).toDouble, line(5).toDouble)).toDF
    val userTopNDF = userDF.orderBy("time").limit(10)
    /**
      * 将统计结果写入到MySQL中
      */
    try {
      userTopNDF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[User]

        partitionOfRecords.foreach(info => {
          //(mobile, bs, time, x, y)
          val day = info.getAs[String]("day")
          val mobile = info.getAs[Long]("mobile")
          val bs = info.getAs[String]("bs")
          val time = info.getAs[String]("time")
          val x = info.getAs[Double]("x")
          val y = info.getAs[Double]("y")
          list.append(User(day, mobile, bs, time, x, y))
        })

        UserDao.insertUserTonN(list)
      })
    } catch {
      case e:Exception => e.printStackTrace()
    }
//    userDF.createOrReplaceTempView("user")
//    val result = spark.sql("select * from user order by time limit 10")//.show
//
//    // 入库 (将结果写入MySQL中)
//    val list = new ListBuffer[User]
//    for (user <- result) {
//      // (mobile, bs, time, x, y)
//      val mobile = user(0)
//      val bs = user(1)
//      val time = user(2)
//      val x = user(3)
//      val y = user(4)
//      list.append(User(mobile, bs, time, x, y))
//    }
//    UserDao.insertUserTonN(list)

  }
}
