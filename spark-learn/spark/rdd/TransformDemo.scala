package spark.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

/**
  * 测试 transform
  */
object TransformDemo {

  def main(args: Array[String]): Unit = {
    // spark1.6

    val conf = new SparkConf()
    conf.setAppName("UserLocationClean").setMaster("local[2]")

    val sc = new SparkContext(conf)

    // spark2
    val sparks = SparkSession.builder().appName("TransformDemo").master("local[2]").getOrCreate()
    val rdd = sc.parallelize(Array(1, 2, 3, 4, 5, 6, 7), 2)
  }
}
