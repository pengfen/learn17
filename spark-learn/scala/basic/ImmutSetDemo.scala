package scala.basic

/**
  * 不可变的 Set
  */
object ImmutSetDemo {

  def main(args: Array[String]): Unit = {
    val set1 = new scala.collection.immutable.HashSet[Int]()

    // 将元素和set1 合并生成一个新的set 原有set不变
    val set2 = set1 + 4

    // set 中元素不能重复
    val set3 = set1 ++ Set(5, 6, 7)
    val set0 = Set(1, 3, 4)++ set1
    println(set0.getClass())
  }
}

//scala> import scala.collection.immutable._
//import scala.collection.immutable._
//
//scala> val hs = new HashSet[Int]
//hs: scala.collection.immutable.HashSet[Int] = Set()
//
//scala> hs + 1
//res59: scala.collection.immutable.HashSet[Int] = Set(1)
//
//scala> hs
//res60: scala.collection.immutable.HashSet[Int] = Set()
//
//scala> res59
//res61: scala.collection.immutable.HashSet[Int] = Set(1)
//
//scala> lb
//res62: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//
//scala> lb ++ res59
//res63: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1)
