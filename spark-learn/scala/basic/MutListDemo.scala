package scala.basic

import scala.collection.mutable.ListBuffer

/**
  * 可变的序列 import scala.collection.mutable._
  *
  */
object MutListDemo {

  def main(args: Array[String]): Unit = {
    // 构建一个可变列表 初始有三个元素 1,2,3
    val lst0 = ListBuffer[Int](1, 2, 3)

    // 创建一个空的可变列表
    val lst1 = new ListBuffer[Int]

    // 向lst1中追加元素 注意 没有生成新的集合
    lst1 += 4
    lst1.append(5)

    // 将 lst1 中的元素最近到lst0中 注意 没有生成新的集合
    lst0 ++= lst1

    // 将 lst0 和 lst1 合并成一个新的ListBuffer 注意 生成了一集合
    val lst2 = lst0 ++ lst1

    // 将元素追加到lst0的后右生成一个新的集合
    val lst3 = lst0 :+ 5
  }

}

//scala> val lb = ListBuffer(1)
//lb: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1)
//
//scala> lb += 2
//res45: lb.type = ListBuffer(1, 2)
//
//scala> lb
//res46: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2)
//
//scala> lb.append(3)
//
//scala> lb
//res49: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3)
//
//scala> lb.append(4, 5, 6, 7)
//
//scala> lb
//res51: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3, 4, 5, 6, 7)
//
//scala> lb += (8, 9)
//res52: lb.type = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9)

//scala> val lb2 = ListBuffer(10)
//lb2: scala.collection.mutable.ListBuffer[Int] = ListBuffer(10)
//
//scala> lb
//res53: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9)
//
//scala> lb2
//res54: scala.collection.mutable.ListBuffer[Int] = ListBuffer(10)
//
//scala> lb ++= lb2
//res55: lb.type = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//
//scala> lb ++ lb2
//res56: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10)
//
//scala> lb
//res57: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//
//scala> lb2
//res58: scala.collection.mutable.ListBuffer[Int] = ListBuffer(10)
