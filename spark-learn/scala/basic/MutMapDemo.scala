package scala.basic

import scala.collection.mutable

/**
  * 可变的 map
  */
object MutMapDemo {
  def main(args: Array[String]): Unit = {
    val map1 = new mutable.HashMap[String, Int]()
    // 向 map 中添加数据
    map1("spark") = 1
    map1 += (("hadoop", 2))
    map1.put("storm", 3)
    println(map1)

    // 从 map 中移除元素
    map1 -= "spark"
    map1.remove("hadoop")
    println(map1)
  }
}
