package scala.basic

/**
  * scala 中集合有可变(mutable)和不可变(immutable)两种类型
  *
  * immutable类型的集合初始化后就不能改变了(注意与val修饰的变量进行区别)
  *
  * 序列
  * 不可变的序列 import scala.collection.immutable._
  *
  * scala中列表要么为空(Nil表示空列表) 要么是一个head元素加上一个tail列表
  *
  * 9::List(5, 2)  ::操作符是将给定的头和尾创建一个新的列表
  * 注意 :: 操作是右结合的 如 9 :: 5 :: 2 :: Nil 相当于 9 :: (5 :: (2 :: Nil))
  *
  */
object ListDemo {
  def main(args: Array[String]): Unit = {
    // 创建一个不可变的集合
    val lst1 = List(1, 2, 3)

    // 将0插入到lst1的前面生成一个新的List
    val lst2 = 0::lst1
    val lst3 = lst1.::(0)
    val lst4 = 0 +: lst1
    val lst5 = lst1 .+:(0)

    // 将一个元素添加到lst1的后面产生一个新的集合
    val lst6 = lst1 :+ 3

    val lst0 = List(4, 5, 6)
    // 将2个list合并成一个新list

    val lst7 = lst1 ++ lst0
    // 将lst1插入到lst0前面生成一个新的集合

    val lst8 = lst1 ++: lst0
    // 将lst0插入到lst1前面生成一个新的集合

    val lst9 = lst1.:::(lst0)

    println(lst9)
  }
}

//scala> val list = List(1, 2, 3)
//list: List[Int] = List(1, 2, 3)
//
//scala> list(0)
//res40: Int = 1
//
//scala> import scala.collection.mutable.ListBuffer
//import scala.collection.mutable.ListBuffer
//
//scala> val lb = ListBuffer(1, 2, 3)
//lb: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3)
//
//scala> lb(1) = 200
//
//scala> lb
//res42: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 200, 3)

//scala> val lst3 = lst1.::(0)
//lst3: List[Int] = List(0, 1, 2, 3)
//
//scala> val lst4 = 0 +: lst1
//lst4: List[Int] = List(0, 1, 2, 3)
//
//scala> lst1
//res44: List[Int] = List(1, 2, 3)
//
//scala> val lst6 = lst1 :+ 3
//lst6: List[Int] = List(1, 2, 3, 3)


//scala> lb.map(_ * 10)
//res43: scala.collection.mutable.ListBuffer[Int] = ListBuffer(10, 2000, 30)
//

//scala> val lst1 = List(1, 2, 3)
//lst1: List[Int] = List(1, 2, 3)
//
//scala> val lst2 = 0 :: lst1
//lst2: List[Int] = List(0, 1, 2, 3)

//scala> val lst8 = lst1 :+ 7
//lst8: List[Int] = List(1, 2, 3, 7)
