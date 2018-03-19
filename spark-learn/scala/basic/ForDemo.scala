package scala.basic

/**
  * 循环有for 和 while
  *
  * for (i <- 表达式/数组/集合)
  *
  * for (i <- 0 to 5) println(i)
  * for (i <- 0 until 10) println(i)
  * for (i <- 1 to 3; j <- 1 to 3 if i != j) {println((10 * i + j) + " ")}
  * for (i <- 1 to 10 if i % 2 == 0) yield i
  * for (i <- 1 to 10) yield i % 3
  *
  */
object ForDemo {
  def main(args: Array[String]): Unit = {
    // for(i <- 表达式）表达式 1 to 10 返回一个Range(区间)
    // 每次循环将区间中的一个值赋给i
    for (i <- 1 to 10)
      println(i)

    // for (i <- 数组)
    val arr = scala.Array("a", "b", "c")
    for (i <- arr)
      println(i) // a b c

    // 高级for循环
    // 每个生成器都可以带一个条件 注意 if前面没有分号
    for (i <- 1 to 3; j <- 1 to 3 if i != j)
      print((10 * i + j) + " ")
    println() // 12 13 21 23 31 32

    // for 推导式 如果for循环的循环体以yield开始 则该循环会构建出一个集合
    // 每次迭代生成集合中的一个值
    // yield 生成一个新的集合
    val v = for (i <- 1 to 10) yield i * 10
    println(v) // Vector(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
  }

}

//scala> 1 to 10
//res0: scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//
//scala> for (i <- res0) println(i)
//1
//2
//3
//4
//5
//6
//7
//8
//9
//10
//
//scala> val arr = Array(1, 2, 3)
//arr: Array[Int] = Array(1, 2, 3)
//
//scala> for (i <- arr) print(i)
//123
//scala> val t = for (i <- 1.to(10)) yield i * 10
//t: scala.collection.immutable.IndexedSeq[Int] = Vector(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

//scala> 1.to(10).map(_ * 10)
//res5: scala.collection.immutable.IndexedSeq[Int] = Vector(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
//
//scala> 1.to(10).map(x => x * 10)
//res6: scala.collection.immutable.IndexedSeq[Int] = Vector(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

// 求数组中的偶数
//scala> val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
//arr: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
//
//scala> arr.filter(_ % 2 == 0)
//res8: Array[Int] = Array(2, 4, 6, 8)

// to 方法 (Int没有 ---> 隐式转换 ---> RichInt)
//scala> val arr = 1.to(10)
//arr: scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//
//scala> arr.filter(_ % 2 == 0)
//res9: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6, 8, 10)
// 获取数组下标
//scala> for (i <- arr) print(i)
//12345678910
//scala>
//
//scala> 0 to arr.length
//res11: scala.collection.immutable.Range.Inclusive = Range(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//
//scala> for (i <- 0 until arr.length) println(arr(i))
