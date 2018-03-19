package spark.sql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
;

/**
  * 1. 编写代码
  *
  * 2. 编写 person.txt
  * 1,caopeng1,18
  * 2,caopeng2,20
  * 3,caopeng3,30
  *
  * 3. 上传 person.txt 至 hdfs
  * hadoop fs -put person.txt /
  * hadoop fs -ls /
  *
  */
object SQLDemo {

  def main(args: Array[String]): Unit = {
//    if (args.length != 1) {
//      // hdfs://ricky:9200/person.txt
//      System.err.println("Usage: <SQLDemo> in_path ")
//      System.exit(1)
//    }

    val conf = new SparkConf().setAppName("SQLDemo").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    System.setProperty("user.name", "ricky")
    val personRdd = sc.textFile("hdfs://ricky:9000/person.txt").map(line => {
      val fields = line.split(",")
      println(fields(0))
      //Person(fields(0).toLong, fields(1), fields(2).toInt)
    })

//    import sqlContext.implicits._
//    val personDf = personRdd.toDF()
//
//    personDf.registerTempTable("t_person")
//
//    sqlContext.sql("select * from t_person where age >= 20 order by age desc limit 2").show()
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  3|caopeng3| 30|
//      |  2|caopeng2| 20|
//      +---+--------+---+

    sc.stop()
  }
}
