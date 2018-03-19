package spark.stream

import java.sql.DriverManager

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
;

/**
  * 使用Spark Streaming完成词频统计，并将结果写入到MySQL数据库中
  *
  * spark_learn数据库建表
  *  create table wordcount (
  * word varchar(50) default null,
  * wordcount int(10) default null
  * )
  *
  */
object ForeachRDDApp {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("ForeachRDDApp").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(5))


    val lines = ssc.socketTextStream("localhost", 6789)

    val result = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

    //result.print()  //此处仅仅是将统计结果输出到控制台

    //TODO... 将结果写入到MySQL ---> 存在的问题
    // 1 对于已有的数据做update 而不是所有的数据做insert ---> 优化
    // 在插入数据前先判断单词是否存在 如果存在就update 不存在则insert
    // 工作中 使用 HBase/Redis (直接使用API就...)
    // 2 每个rdd的partition创建connection 改成连接池
    //    result.foreachRDD(rdd =>{
    //      val connection = createConnection()  // executed at the driver
    //      rdd.foreach { record =>
    //        val sql = "insert into wordcount(word, wordcount) values('"+record._1 + "'," + record._2 +")"
    //        connection.createStatement().execute(sql)
    //      }
    //    })


    result.print()

    result.foreachRDD(rdd => {
      rdd.foreachPartition(partitionOfRecords => {
        val connection = createConnection()
        partitionOfRecords.foreach(record => {
          val sql = "insert into wordcount(word, wordcount) values('" + record._1 + "'," + record._2 + ")"
          connection.createStatement().execute(sql)
        })

        connection.close()
      })
    })


    ssc.start()
    ssc.awaitTermination()
  }


  /**
    * 获取MySQL的连接
    */
  def createConnection() = {
    Class.forName("com.mysql.jdbc.Driver")
    DriverManager.getConnection("jdbc:mysql://localhost:3306/spark_learn", "root", "123456")
  }

}
