package scala.advance

/**
  * 高阶函数
  * def fun(f: Double => Double) = {f(100)}
  * fun((x: Double) => 2 * x)
  * fun(sqrt _)
  * val fun: Int => (Int, Int) = {x => (x, x * 2)}
  * fun(100)
  * val fun: (Int, Double) => (Double, Int) = {(x,y) => (y, x)}
  */
object HighFunc {

  val func: Int => Int = {x => x * x}

  def multiply (x: Int): Int = x * x

  def m1(x: Int)(y: Int) = x * y

  def m2(x: Int) = (y: Int) => x * y

  def multi = (x: Int) => {
    x * x
  }
  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 4, 5)

    val a1 = arr.map(func)

    println(a1.toBuffer)

    val a2 = arr.map(multi)

    println(a2.toBuffer)

  }
}

//scala> val func = (x: Int) => x * x
//func: Int => Int = <function1>
//
//scala> func(5)
//res6: Int = 25
//
//scala> val arr = Array(1, 2, 3, 4, 5, 6, 7, 8)
//arr: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8)
//
//scala> arr.map(func)
//res7: Array[Int] = Array(1, 4, 9, 16, 25, 36, 49, 64)
//
//scala>
//
//scala> def multiply(x: Int): Int = x * x
//multiply: (x: Int)Int
//
//scala> multiply(3)
//res8: Int = 9
//
//scala> arr.map(multiply _)
//res9: Array[Int] = Array(1, 4, 9, 16, 25, 36, 49, 64)
//
//scala> val m = multiply _
//m: Int => Int = <function1>
//
//scala> arr.map(multiply)
//res10: Array[Int] = Array(1, 4, 9, 16, 25, 36, 49, 64)

//scala> def mul = (x: Int) => {x * x}
//mul: Int => Int
//
//scala> mul
//res12: Int => Int = <function1>
//
//scala> def mul() = (x: Int) => {x * x}
//mul: ()Int => Int
//
//scala> mul()
//res13: Int => Int = <function1>
//
//scala> mul _
//res14: () => Int => Int = <function0>
// 柯里化
//scala> def klh(x: Int)(y: Int) = x * y
//klh: (x: Int)(y: Int)Int
//
//scala> klh(3)(4)
//res15: Int = 12
//
//scala> klh(3)(_)
//res16: Int => Int = <function1>
//
//scala> res16(4)
//res18: Int = 12
