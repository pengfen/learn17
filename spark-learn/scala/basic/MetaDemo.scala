package scala.basic

/**
  * 元组
  * 元组是不同类型元素的集合
  * val t = ("hadoop", 1000, "spark")
  * val a = t._3 //元组中的下标是从1开始
  * "NewYork".partition(_.isUpper)
  *
  * 下划线
  */
object MetaDemo {
  def main(args: Array[String]): Unit = {
    // 1. 定义元组时用小括号将多个元素包起来 元素之间用逗号分隔 元素的类型可以不一样 元素个数可以任意多个
    val t = ("hadoop", 3.14, 65535)
    println(t)
    //    t: (String, Double, Int) = (hadoop,3.14,65535)

    // 2. 获取元组中的值
    val t1,(a,b,c) = ("hadoop", 3.14, 65535)
    //    t: (String, Double, Int) = (hadoop,3.14,65535)
    //    a: String = hadoop
    //    b: Double = 3.14
    //    c: Int = 65535
    //
    //    scala> val r1 = t._1 // 获取元组中的元素可以使用下划线加脚标 需要注意的是元组中的元素脚标是从1开始的
    println(t1._1)
    //    r1: String = hadoop
    //
    //    scala> val r2 = t._2
    println(t1._2)
    //    r2: Double = 3.14

    // 将对偶的集合转换成映射
    //    scala> val arr = Array(("tom", 88), ("jerry", 95))
    //    arr: Array[(String, Int)] = Array((tom,88), (jerry,95))
    //
    //    scala> arr.toMap // toMap可以将对偶的集合转换成映射
    //    res10: scala.collection.immutable.Map[String,Int] = Map(tom -> 88, jerry -> 95)
  }
}
//scala> val func1 = (x: Int, y: Double) => (y, x)
//func1: (Int, Double) => (Double, Int) = <function2>
//
//scala> func1(2, 3.0)
//res0: (Double, Int) = (3.0,2)
//
//scala> val func2:(Int, Double) => (Double, Int) = {
//| (a, b) => (b, a)
//| }
//func2: (Int, Double) => (Double, Int) = <function2>
//
//scala> func2(2, 3.0)
//res1: (Double, Int) = (3.0,2)

// _ 将方法转换成下划线
//scala> def m2(x: Int, y: Int): Int = x + y
//m2: (x: Int, y: Int)Int
//
//scala> val f2 = (a: Int, b: Int) => a + b
//f2: (Int, Int) => Int = <function2>
//
//scala> val f2 = m2 _
//f2: (Int, Int) => Int = <function2>
//
//scala> f2(1, 2)
//res2: Int = 3
//
//scala> m2(1, 3)
//res3: Int = 4

//scala> val p = println _
//p: () => Unit = <function0>

//scala> val t = ("a", 1, 2.0)
//t: (String, Int, Double) = (a,1,2.0)
//
//scala> t._2
//res34: Int = 1
// 将三个值分别赋值给 x y z
//scala> val t, (x, y, z) = ("a", 1, 2.0)
//t: (String, Int, Double) = (a,1,2.0)
//x: String = a
//y: Int = 1
//z: Double = 2.0
//
//scala> x
//res35: String = a
