package scala.collect

/**
  * 集合练习
  */
object ListTest {
  def main(args: Array[String]) {
    //创建一个List
    val lst0 = List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2)
    println(lst0) // List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2)

    //将lst0中每个元素乘以10后生成一个新的集合
    val lst1 = lst0.map(x => x * 2)
    println(lst1) // List(2, 14, 18, 16, 0, 6, 10, 8, 12, 4)

    //将lst0中的偶数取出来生成一个新的集合
    val lst2 = lst0.filter(x => x % 2 == 0)
    println(lst2) // List(8, 0, 4, 6, 2)

    //将lst0排序后生成一个新的集合
    val lst3 = lst0.sorted
    println(lst3) // List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val lst4 = lst0.sortBy(x => x)
    println(lst4) // List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val lst5 = lst0.sortWith((x, y) => x < y)
    println(lst5) // List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    //反转顺序
    val lst6 = lst3.reverse
    println(lst6) // List(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)

    //将lst0中的元素4个一组,类型为Iterator[List[Int]]
    val it = lst0.grouped(4)
    println(it) // non-empty iterator

    //将Iterator转换成List
    val lst7 = it.toList
    println(lst7) // List(List(1, 7, 9, 8), List(0, 3, 5, 4), List(6, 2))

    //将多个list压扁成一个List
    val lst8 = lst7.flatten
    println(lst8) // List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2)

    //先按空格切分，在压平
    val a = Array("a b c", "d e f", "h i j")
    a.flatMap(_.split(" "))

    //并行计算求和
    val sum1 = lst0.par.sum
    println(sum1) // 45
    val sum2 = lst0.par.map(_ % 2 == 0)
    lst0.par.reduce((x, y) => x + y)

    //化简：reduce
    //将非特定顺序的二元操作应用到所有元素
    val lst9 = lst0.reduce((x, y) => x + y)
    println(lst9) // 45

    //安装特点的顺序
    val lst10 = lst0.reduceLeft(_+_)
    println(lst10) // 45

    //折叠：有初始值（无特定顺序）
    val lst11 = lst0.fold(100)((x, y) => x + y)
    println(lst11) // 145

    //折叠：有初始值（有特定顺序）
    val lst12 = lst0.foldLeft(100)((x, y) => x + y)
    println(lst12) // 145

    //聚合
    val arr = List(List(1, 2, 3), List(3, 4, 5), List(2), List(0))
    val result = arr.aggregate(10)(_+_.sum, _+_)
    println(result) // 30

    val l1 = List(5, 6, 4, 7)
    val l2 = List(1, 2, 3, 4)

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
    println(m) // Map(a -> 1)
  }
}
