package scala.basic

/**
  * 数组转换
  * 转换动作不会修改原始数组 而是产生一个全新的数组
  * val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) for(e <- arr) yield e // 生成一个与arr一样的数组
  *
  * yield关键字 将原始的数组进行转换会产生一个新的数组 原始的数组不变
  *
  * scala> val a = Array(1, 2, 3, 4, 5) // 定义一个数组
  * a: Array[Int] = Array(1, 2, 3, 4, 5)
  *
  * scala> for (i <- a) yield i * 10 // 使用yield关键字生成一个新的数组
  * res0: Array[Int] = Array(10, 20, 30, 40, 50)
  *
  * scala> a.map(_ * 10) // map方法更加方便
  * res5: Array[Int] = Array(10, 20, 30, 40, 50)
  */
object YieldArray {
  def main(args: Array[String]): Unit = {
    // 定义一个数组
    val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9);
    // 将偶数取出乘以 10 后再生成一个新的数组
    val res = for (e <- arr if e % 2 == 0) yield e * 10
    println(res.toBuffer)

    // 更高级的写法 用着更爽
    // filter是过滤　接收一个返回值为boolean的函数
    // map相当于将数组中的每一个元素取出来　应用传进去的函数
    val r = arr.filter(_ % 2 == 0).map(_ * 10)
    println(r.toBuffer)
  }
}
//scala> val a = Array(1, 2, 3, 4, 5)
//a: Array[Int] = Array(1, 2, 3, 4, 5)
//
//scala> for (i <- a) yield i * 10
//res0: Array[Int] = Array(10, 20, 30, 40, 50)
//
//scala> a
//res1: Array[Int] = Array(1, 2, 3, 4, 5)
//
//scala> a.map((x:Int) => x * 10)
//res2: Array[Int] = Array(10, 20, 30, 40, 50)
//
//scala> a
//res3: Array[Int] = Array(1, 2, 3, 4, 5)
//
//scala> a.map(x => x * 10)
//res4: Array[Int] = Array(10, 20, 30, 40, 50)
//
//scala> a.map(_ * 10)
//res5: Array[Int] = Array(10, 20, 30, 40, 50)

//scala> val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
//arr: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
// 求数组中的偶数
//scala> arr.filter((x: Int) => x % 2 == 0)
//res6: Array[Int] = Array(2, 4, 6, 8)
//
//scala> arr.filter(x => x % 2 == 0)
//res7: Array[Int] = Array(2, 4, 6, 8)
//
//scala> arr.filter(_ % 2 == 0)
//res8: Array[Int] = Array(2, 4, 6, 8)
//
//scala> arr.filter(_ % 2 == 0).map(_ * 10)
//res9: Array[Int] = Array(20, 40, 60, 80)

//scala> val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
//arr: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
// 求和
//scala> arr.sum
//res10: Int = 45

//scala> val arr = Array(2, 5, 1, 4, 7, 3)
//arr: Array[Int] = Array(2, 5, 1, 4, 7, 3)
// 排序
//scala> arr.sorted
//res11: Array[Int] = Array(1, 2, 3, 4, 5, 7)
// 排序后反转
//scala> arr.sorted.reverse
//res12: Array[Int] = Array(7, 5, 4, 3, 2, 1)
//
//scala> arr.sortBy(x => x)
//res13: Array[Int] = Array(1, 2, 3, 4, 5, 7)
//
//scala> arr
//res14: Array[Int] = Array(2, 5, 1, 4, 7, 3)

//scala> arr.sortWith(_>_)
//res15: Array[Int] = Array(7, 5, 4, 3, 2, 1)
//
//scala> arr.sortWith(_<_)
//res16: Array[Int] = Array(1, 2, 3, 4, 5, 7)

//scala> arr.sortWith((x, y) => x > y)
//res17: Array[Int] = Array(7, 5, 4, 3, 2, 1)
//
//scala> arr.sortWith((x, y) => x < y)
//res18: Array[Int] = Array(1, 2, 3, 4, 5, 7)

//    scala> val arr = Array(2, 5, 1, 4, 3)
//    arr: Array[Int] = Array(2, 5, 1, 4, 3)
//
//    scala> arr.sum // 求和
//    res1: Int = 15
//
//    scala> arr.max // 求最大值
//    res2: Int = 5
//
//    scala> arr.sorted // 排序
//    res3: Array[Int] = Array(1, 2, 3, 4, 5)