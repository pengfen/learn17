package scala.basic

import scala.collection.mutable.ArrayBuffer

/**
  * 变长数组
  * ArrayBuffer类似Java中的ArrayList
  * val a = ArrayBuffer[Int]()或val a = new ArrayBuffer[Int]
  * a += 1 //在尾端添加元素
  * a += (1, 2, 3) // 追加多个元素
  * a ++= ArrayBuffer(7, 8, 9) // 追加集合
  * a.trimEnd(2) // 移除最后两个元素
  * a.insert(2, 6, 7) // 从下标2之前插入6, 7
  * a.remove(2, 2) // 从下标2开始移除2个元素
  * val b = a.toArray // 转变成定长数据组
  * b.toBuffer // 转变成变长数据组
  */
object ArrayBufferDemo {
  def main(args: Array[String]): Unit = {
    // 变长数组(数组缓冲)
    // 如果想使用数组缓冲 需要导入 import scala.collection.mutable.ArrayBuffer 包
    val ab = ArrayBuffer[Int]()

    // 向数组缓冲的尾部追加一个元素
    // +=尾部追加元素
    ab += 1
    // 追加多个元素
    ab += (2,3,4,5)
    // 追加一个数组
    //ab ++= Array(6,7)
    // 追加一个数组缓冲
    ab ++= ArrayBuffer(8,9)
    // 打印数组缓冲ab
    println(ab) // ArrayBuffer(1, 2, 3, 4, 5, 8, 9)


    // 在数组某个位置插入元素用insert
    ab.insert(0, -1, 0)
    // 删除数组某个位置的元素用remove
    ab.remove(7, 2)
    println(ab)
  }
}
//scala> import scala.collection.mutable.ArrayBuffer
//import scala.collection.mutable.ArrayBuffer
//
//scala> val ab = new ArrayBuffer[Int]()
//ab: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
//
//scala> ab += 1
//res7: ab.type = ArrayBuffer(1)
//
//scala> ab
//res8: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1)
//
//scala> ab += 2
//res9: ab.type = ArrayBuffer(1, 2)
//
//scala> ab += 3
//res10: ab.type = ArrayBuffer(1, 2, 3)
//
//scala> ab += (4, 5, 6)
//res11: ab.type = ArrayBuffer(1, 2, 3, 4, 5, 6)


//scala> ab ++= Array(7, 8, 9)
//res12: ab.type = ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9)
//
//scala> ab
//res13: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9)
//
//scala> ab.insert(1, 2, 3) // 下标为１的位置添加元素 2 3
//
//scala> ab
//res15: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 2, 3, 4, 5, 6, 7, 8, 9)
//
//scala> ab.insert(8, 2, 3) // 下标为8的位置添加元素 2 3
//
//scala> ab
//res17: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 2, 3, 4, 5, 6, 2, 3, 7, 8, 9)


//scala> val a = new ArrayBuffer[Int]()
//a: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
//
//scala> a += 1
//res18: a.type = ArrayBuffer(1)
//
//scala> a += 2
//res19: a.type = ArrayBuffer(1, 2)
//
//scala> a += 3
//res20: a.type = ArrayBuffer(1, 2, 3)
//
//scala> a.insert(1, 6) // 下标为1的位置添加元素 6
//
//scala> a
//res22: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 6, 2, 3)
//
//scala> a.insert(0, 8) // 下标为0的位置添加元素 8
//
//scala> a
//res24: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(8, 1, 6, 2, 3)

//scala> a.remove(1, 2) // 下标为1的位置开始移除数据 移除二个
//
//scala> a
//res26: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(8, 2, 3)
