package scala.basic

/**
  * 定长数组
  * //3个整数的数据　所有元素为0
  * val arr1 = new Array[Int](3)
  * val arr2 = Array(0, 0, 0)
  * val arr3 = Array("hadoop", "spark"
  * // 取值　赋值
  * val str = arr3(1)
  * arr3(1) = "storm"
  */
object ArrayDemo {

  def main(args: Array[String]): Unit = {
    // 1. 定长数组和变长数组 (ArrayBufferDemo)

    // 初始化一个长度为8的定长数组 其所有元素均为0
    val arr1 = new Array[Int](8)
    // 直接打印定长数据 内容为数据的hashcode值
    println(arr1)

    // 将数组转换成数组缓冲 就可以看到原数组中的内容
    // toBuffer 会将数组转换长数组缓冲
    println(arr1.toBuffer)

    // 注意 如果new 相当于调用了数组的apply方法 直接为数组赋值
    // 初始化一个长度为1的定长数组
    val arr2 = Array[Int](10)
     println(arr2.toBuffer)

     //定义一个长度为3的定长数组
    val arr3 = Array("hadoop", "storm", "spark")
    // 使用()来访问元素
    println(arr3(2))

    // 2. 遍历数组 (ForArray)

    // 3. 数据转换 (MapArray)

  }
}
//scala> val arr = new Array[Int](10)
//arr: Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
//
//scala> arr(0) = 1
//
//scala> arr(1) = 2
//
//scala> arr
//res6: Array[Int] = Array(1, 2, 0, 0, 0, 0, 0, 0, 0, 0)
//
//scala>
//
//scala> val arr1 = Array(1, 3, 4, 5, 6)
//arr1: Array[Int] = Array(1, 3, 4, 5, 6)

//scala> val arr = new Array[Int](10)
//arr: Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
//scala> val arr1 = Array[Int](10)
//arr1: Array[Int] = Array(10)

