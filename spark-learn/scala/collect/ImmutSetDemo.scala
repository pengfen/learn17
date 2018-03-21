package scala.collect

import scala.collection.immutable.HashSet

/**
  * 不可变的 Set
  */
object ImmutSetDemo extends App{

  val set1 = new HashSet[Int]()
  println(set1) // Set()

  //将元素和set1合并生成一个新的set，原有set不变
  val set2 = set1 + 4
  println(set2) // Set(4)

  //set中元素不能重复
  val set3 = set1 ++ Set(5, 6, 7)
  println(set3) // Set(5, 6, 7)
  val set0 = Set(1,3,4) ++ set1
  println(set0.getClass) // class scala.collection.immutable.Set$Set3

}
