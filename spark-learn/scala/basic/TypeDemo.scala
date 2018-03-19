package scala.basic

/**
  * Scala和Java一样 有七种数值类型Byte Char Short Int Long Float Double
  *
  * 无包装类型
  *
  * if 判断
  * val a = if (x > 0) 1 else -1
  * // 混合类型
  * val b = if (x > 0) 1 else "error"
  * // ()表示"无有用值"
  * val c = if (x > 0) 1 else ()
  *
  * 类型 Byte Char Short Int Long Float Double Boolean

scala> "Hello".intersect("World") # intersect 方法输出两个字符串共通的一组字符
res0: String = lo

scala> 99.44.toInt
res1: Int = 99

scala> 99.toChar
res2: Char = c

scala> 99.44.toDouble
res3: Double = 99.44

# 操作符实际上是方法
scala> val a = 10
a: Int = 10

scala> val b = 20
b: Int = 20

scala> a + b
res4: Int = 30

scala> a.+(b)
res6: Int = 30

# scala 不提供 ++ -- 操作符
scala> var counter = 1
counter: Int = 1

scala> counter += 1

scala> counter
res10: Int = 2

scala> "crazy" * 3 # 字符串 * 数字
res12: String = crazycrazycrazy

scala> x
res13: Int = 100
  *
  */
object TypeDemo {

  def main(args: Array[String]): Unit = {
    // 条件表达式
    val x = 1
    // 判断x的值 将结果赋给y
    val y = if (x > 0) 1 else -1
    // 打印y的值
    println(y) // 1

    // 支持混合类型表达式 （z有可能是int也可能是string）
    val z = if (x > 1) 1 else "error"
    println(z) // error

    // 如果缺失else 相当于 if(x > 2) 1 else ()
    val m = if (x > 2) 1
    println(m) // ()

    // 在scala中每个表达式都有值 scala中有个Unit类 写做() 相当于java中的void
    val n = if (x > 2) 1 else ()
    println(n) // ()

    // if 和 else if
    val k = if (x < 0) 0 else if (x >= 1) 1 else -1
    println(k) // 1
  }
}

//scala> val x = 10
//x: Int = 10
//
//scala> val y = if (x > 0) 1 else -1
//y: Int = 1
//
//scala> val z = if (x > 1) 1 else "error"
//z: Any = 1
//
//scala> val x = 0
//x: Int = 0
//
//scala> val z = if (x > 1) 1 else "error"
//z: Any = error
// 
//scala> val m = if (x > 2) 1
//m: AnyVal = ()
//
//scala> val k = if (x < 0) 0 else if (x >= 1) 1 else -1
//k: Int = -1
