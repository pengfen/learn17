package scala.basic

/**
  * 集合练习
  */
object Learn {

  def main(args: Array[String]): Unit = {
    // 创建一个List
    val lst0 = List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2);

    // 将lst0中每个元素乘以10后生成一个新的集合
    // val lst1 = lst0.map((x: Int) => x * 10)
    // val lst1 = lst0.map(x => x * 10)
    val lst1 = lst0.map(_ * 10)
    println(lst1) // List(10, 70, 90, 80, 0, 30, 50, 40, 60, 20)

    // 将lst0中的偶数取出来生成一个新的集合
    // val lst2 = lst0.filter((x: Int) => x % 2 == 0)
    // val lst2 = lst0.filter(x => x % 2 == 0)
    val lst2 = lst0.filter(_ % 2 == 0)
    println(lst2) // List(8, 0, 4, 6, 2)

    // 将lst0集合中的偶数取出来生成一个乘以10的集合
    // val lst = lst0.filter(x => x % 2 == 0).map(y => y * 10)
    val lst = lst0.filter(_ % 2 == 0).map(_ * 10)
    println(lst) // List(80, 0, 40, 60, 20)

    // 将lst0排序后生成一个新的集合
    // val lst3 = lst0.sorted
    // val lst3 = lst0.sortBy(x => x)
    // val lst3 = lst0.sortWith((x, y) => x < y)
    val lst3 = lst0.sortWith(_<_)
    println(lst3) // List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    // 反转顺序
    //val lst4 = lst0.sorted.reverse
    val lst4 = lst3.reverse
    println(lst4) // List(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)

    // 将lst0中的元素4个一组 类型为Iterator[List[Int]]
    val it = lst0.grouped(4)
    println(it) // non-empty iterator

    // 将Iterator转换成List
    val lst5 = it.toList
    println(lst5) // List(List(1, 7, 9, 8), List(0, 3, 5, 4), List(6, 2))

    // 将多个list压扁成一个List
    val lst6 = lst5.flatten
    println(lst6) // List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2)

    // 先按空格切分 再压平
    val a = Array("a b c", "d e f", "h i j")
    val lst7 = a.flatMap(_.split(" "))
    println(lst7) // [Ljava.lang.String;@4e718207

    val lines = List("hello tom hello jerry", "hello jerry", "hello kitty")
    val lst8 = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2))
    println(lst8) // Map(tom -> 1, kitty -> 1, jerry -> 2, hello -> 4)
    val lst9 = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(t=>(t._1, t._2.size)).toList.sortBy(_._2).reverse
    println(lst9) // List((hello,4), (jerry,2), (kitty,1), (tom,1))

    //并行计算求和
    //val lst0 = List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2);
    val lst11 = lst0.sum;
    println(lst11) // 45
    lst0.par // 并行

    //化简：reduce
    val lst12 = lst0.par.reduce(_+_) // 并行求和
    println(lst12) // 45
    //将非特定顺序的二元操作应用到所有元素
    //reduce ---> reduceLef

    //按照特定的顺序

    //折叠：有初始值（无特定顺序）
    // val lst13 = lst0.par.fold(0)(_+_)
    val lst13 = lst0.fold(100)((x, y) => x + y) // 145
    println(lst13)

    //折叠：有初始值（有特定顺序）
    //val lst14 = lst0.foldLeft(0)(_+_)
    val lst14 = lst0.foldLeft(100)((x, y) => x + y) // 145
    println(lst14)

    //聚合
    val arr = List(List(1, 2, 3), List(3, 4, 5), List(2), List(0))
    val result = arr.aggregate(10)(_+_.sum, _+_) // 30
    println(result)

    val l1 = List(5,6,4,7)
    val l2 = List(1,2,3,4)
    //求并集
    val r1 = l1.union(l2)
    println(r1) // List(5, 6, 4, 7, 1, 2, 3, 4)

    //求交集
    val r2 = l1.intersect(l2)
    println(r2) // List(4)

    //求差集
    val r3 = l1.diff(l2)
    println(r3) // List(5, 6, 7)

    val m = Map(("a", 1))
  }
}

//scala> val lst = List(1, 3, 2, 7, 9, 6, 4, 8)
//lst: List[Int] = List(1, 3, 2, 7, 9, 6, 4, 8)
//
//scala> lst.sorted
//res64: List[Int] = List(1, 2, 3, 4, 6, 7, 8, 9)
//
//scala> val lst1 =lst.sorted
//lst1: List[Int] = List(1, 2, 3, 4, 6, 7, 8, 9)
//
//scala> lst1.grouped(5)
//res65: Iterator[List[Int]] = non-empty iterator
//
//scala> val it = lst1.grouped(5)
//it: Iterator[List[Int]] = non-empty iterator
//
//scala> it.toList
//res66: List[List[Int]] = List(List(1, 2, 3, 4, 6), List(7, 8, 9))
//
//scala> res66 .iterator
//res67: Iterator[List[Int]] = non-empty iterator

//scala> lst1
//res68: List[Int] = List(1, 2, 3, 4, 6, 7, 8, 9)
//
//scala> lst1.grouped(5)
//res69: Iterator[List[Int]] = non-empty iterator
//
//scala> val it = lst1.grouped(5)
//it: Iterator[List[Int]] = non-empty iterator
//
//scala> val lst2 = it.toList
//lst2: List[List[Int]] = List(List(1, 2, 3, 4, 6), List(7, 8, 9))
// 压平 将多级List压成一个List
//scala> lst2.flatten
//res70: List[Int] = List(1, 2, 3, 4, 6, 7, 8, 9)

//scala> val lines = List("wel tom wel jerry", "wel tom kitty wel wel")
//lines: List[String] = List(wel tom wel jerry, wel tom kitty wel wel)
//
//scala> lines.map(_.split(" "))
//res71: List[Array[String]] = List(Array(wel, tom, wel, jerry), Array(wel, tom, kitty, wel, wel))
//
//scala> lines.map(_.split(" "))
//res72: List[Array[String]] = List(Array(wel, tom, wel, jerry), Array(wel, tom, kitty, wel, wel))
//
//scala> lines.map(_.split(" ")).flatten
//res73: List[String] = List(wel, tom, wel, jerry, wel, tom, kitty, wel, wel)
//
//scala> val words = lines.flatMap(_.split(" "))
//words: List[String] = List(wel, tom, wel, jerry, wel, tom, kitty, wel, wel)
//
//scala> val wordsAndOne = words.map((_, 1))
//wordsAndOne: List[(String, Int)] = List((wel,1), (tom,1), (wel,1), (jerry,1), (wel,1), (tom,1), (kitty,1), (wel,1), (wel,1))
//
//scala> val grouped = wordsAndOne.groupBy(_._1)
//grouped: scala.collection.immutable.Map[String,List[(String, Int)]] = Map(wel -> List((wel,1), (wel,1), (wel,1), (wel,1), (wel,1)), tom -> List((tom,1), (tom,1)), kitty -> List((kitty,1)), jerry -> List((jerry,1)))
//
//scala> val result = grouped.map(_._1)
//result: scala.collection.immutable.Iterable[String] = List(wel, tom, kitty, jerry)
//
//scala> val result = grouped.map(t => (t._1, t._2.size))
//result: scala.collection.immutable.Map[String,Int] = Map(wel -> 5, tom -> 2, kitty -> 1, jerry -> 1)
//
//scala> val finalResult = result.toList.sortBy(_._2)
//finalResult: List[(String, Int)] = List((kitty,1), (jerry,1), (tom,2), (wel,5))
//
//scala> val finalResult = result.toList.sortBy(_._2).reverse
//finalResult: List[(String, Int)] = List((wel,5), (tom,2), (jerry,1), (kitty,1))

//scala> val a = Array(1, 2, 3, 4, 5, 6)
//a: Array[Int] = Array(1, 2, 3, 4, 5, 6)
//
//scala> a.sum
//res74: Int = 21
//
//scala> a.reduce(_+_) // ---> reduceLeft ---> ((((1 + 2) + 3) + 4) + 5) + 6
//res75: Int = 21
//
//scala> a.reduce(_-_)
//res76: Int = -19
//
//scala> a.par
//res77: scala.collection.parallel.mutable.ParArray[Int] = ParArray(1, 2, 3, 4, 5, 6)
//
//scala> a.par.reduce(_+_)
//res78: Int = 21
//scala> a.fold(10)(_+_)
//res79: Int = 31
//
//scala> a.par.fold(10)(_+_)
//res80: Int = 81
//
//scala> a.par.fold(0)(_+_)
//res81: Int = 21
//
//scala> a.foldLeft(0)(_+_)
//res82: Int = 21
//
//scala> a.foldRight(10)(_+_)
//res83: Int = 31
//
//scala> grouped
//res84: scala.collection.immutable.Map[String,List[(String, Int)]] = Map(wel -> List((wel,1), (wel,1), (wel,1), (wel,1), (wel,1)), tom -> List((tom,1), (tom,1)), kitty -> List((kitty,1)), jerry -> List((jerry,1)))
//
//scala> val result = grouped.mapValues(_.foldLeft(0)(_+_._2))
//result: scala.collection.immutable.Map[String,Int] = Map(wel -> 5, tom -> 2, kitty -> 1, jerry -> 1)


//scala> def m(x: Int): Int = x * x
//m: (x: Int)Int
//
//scala> m(3)
//res0: Int = 9
//
//scala> val f1 = m _
//f1: Int => Int = <function1>


//scala> val arr = List(List(1, 2, 3), List(3, 4, 5), List(2), List(0))
//arr: List[List[Int]] = List(List(1, 2, 3), List(3, 4, 5), List(2), List(0))
//
//scala> val result = arr.aggregate(0)(_+_.sum, _+_)
//result: Int = 20
//
//scala> val result = arr.aggregate(10)(_+_.sum, _+_)
//result: Int = 30
//
//scala> val result = arr.par.aggregate(10)(_+_.sum, _+_)
//result: Int = 60
