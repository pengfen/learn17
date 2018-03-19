package scala.basic

/**
  * 遍历数组
  *
  * 1. 增强for循环
  *
  * 2. 好用的until会生成下标 0 until 10 (包含0不包含10)
  * scala> 0 until 10
  * res85: scala.collection.immutable.Range = Range(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  *
  * 遍历数组和数组缓冲
  * val arr = Array(1, 2, 3, 4, 5)
  * // 带下标的for循环
  * for (i <- (0 until (arr.length, 2)).reverse) {println(arr(i))}
  * // 增强for循环
  * for (i <- arr) println(i)
  *
  */
object ForArray {
  def main(args: Array[String]): Unit = {
    // 初始化一个数组
    val arr1 = scala.Array(1, 2, 3, 4, 5, 6, 7, 8)
    // 增强for循环
    for (i <- arr1)
    println(i)

    // 好用的until会生成一个Range reverse 是将前面生成的Range反转
    for (i <- (0 until arr1.length).reverse)
    println(arr1(i))

    //yield关键字将原始的数组进行转换会产生一个新的数组 原始的数组不变
    //定义一个数组
    val arr = Array(1, 2, 3, 4, 5, 6, 7, 8)
    //将偶数取出乘以10后再生成一个新的数组
    val res = for (e <- arr if e % 2 == 0) yield e * 10
    println(res.toBuffer)


    // 更高级的写法
    // filter是过滤 接收一个返回值为boolean的函数
    // map 相当于将数组中的每一个元素取出来 应用传进去的函数
    val r = arr.filter(_ % 2 == 0).map(_ * 10)
    println(r.toBuffer)
  }
}
