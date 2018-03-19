package com.spark_rdd

import java.util.Comparator

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 自定义排序
  */
object OrderContext {
  implicit val girlOrdering = new Ordering[Girl] {
    override def compare(x: Girl, y: Girl): Int = {
      if (x.faceValue > y.faceValue) 1
      else if (x.faceValue == y.faceValue) {
        if (x.age > y.age) -1 else 1
      } else -1
    }
  }
}

// sort 规则 先按faceValue 再比较年龄
// name faceValue age
object CustomSort {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CustomSort").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(List(("capeng1", 90, 28, 1), ("caopeng2", 91, 29, 2), ("caopeng3", 70, 26, 3)))
    //import OrderContext._
    val rdd2 = rdd1.sortBy(x => Girl(x._2, x._3), false)
    println(rdd2.collect().toBuffer) // ArrayBuffer((caopeng2,91,29,2), (capeng1,90,28,1), (caopeng3,70,26,3))
    sc.stop()
  }
}

/**
  * @param faceValue 颜值
  * @param age 年龄
  */
case class Girl(val faceValue: Int, val age: Int) extends Ordered[Girl] with Serializable {
  override def compare(that: Girl): Int = {
    if (this.faceValue == that.faceValue) {
      that.age - this.age
    } else  {
      this.faceValue - that.faceValue
    }
  }
}

/**
  * 通过隐式转换完成排序
  * @param faceValue
  * @param age
  */
//case  class Girl(val faceValue: Int, val age: Int) extends Serializable