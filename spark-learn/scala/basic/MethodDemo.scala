package scala.basic

/**
  * Scala中的 + - * / % 等操作符的作用与Java一样 位操作符 & | >> << 也一样 只是有一点特别的
  * 这些操作符实际上是方法 a + b ---> a.+(b)  a 方法 b ---> a.方法(b)
  *
  * 方法
  * a 方法 b 等价于 a.方法(b)
  * val a, b = 10
  * a + b 等价于 a.+(b)
  * 1 to 10 等价于 1.to(10)
  * 函数
  * def fun(x: Int): Int = x + 1
  * //注意: 参数类型必须声明　返回类型可以不声明　函数是递归的必须声明返回类型
  * def fac(n: Int): Int = {if(n <= 0) 1 else n * fac(n - 1)}
  * // 函数默认参数
  * def fun(a: Int, b: Int = 100) = a + b
  * // 可变参数
  * def sum(args: Int*) = {var result = 0; for(i <- args) result += i; println(result)}
  * 无返回值的函数
  * def fun1(x: Int) {println(x)}
  * 或 def fun2(x: Int): Unit = {println(x)}
  * 匿名函数
  * // 没有定义方法名字的函数
  * (x: Int) => x + 1
  * // 将匿名函数赋给变量
  * val fun = (x: Int) => x + 1
  * 
  *
  * 方法和函数的区别
  *
  * 在函数式编程语言中 函数是 "头等公民" 它可以像任何其他数据类型一样被传递和操作
  * def m(f: (Int, Int) => Int) = f(2,6)
  * def f = (x: Int, y: Int) => x - y
  * m(f)
  *
  * 函数可以作为参数传给方法
  */
object MethodDemo {

  /**
    * 注意 方法的返回值类型可以不写 编译器可以自动推断出来 但是对于递归函数 必须指定返回值类型
    *
    * x和y是参数列表   Int方法返回值类型   x * y 方法体
    *
    * def 定义方法使用def关键字
    * me 方法名
    * @param x 参数
    * @param y 参数
    * @return Int 返回值类型
    */
  def me(x: Int, y: Int) : Int = x * y

  // 定义函数
  val fe = (m: Int, n: Int) => m * n

  // 定义一个方法
  // 方法m1参数要求是一个函数 函数的参数必须是两个Int类型
  // 返回值类型是Int类型
  // {} 方法体
  def m1(f: (Int, Int) => Int): Int = {
    // 在方法体里面调用函数
    f(2, 6)
  }

  // 定义一个函数f1 参数是两个Int类型 返回值是一个Int类型
  val f1 = (x: Int, y: Int) => x + y
  // 再定义一个函数
  val f2 = (m: Int, n: Int) => m * n

  def main(args: Array[String]): Unit = {
    // 调用m1方法 并传入f1函数
    val r1 = m1(f1) // ---> m1(f1(2, 6)) ---> 8 将函数作为参数传入到方法中
    println(r1) // 8

    // 调用m1方法 并传入f2函数
    val r2 = m1(f2)
    println(r2)
  }

  // 神奇的下划线 将方法转换成函数 (隐匿转换)
  val ff = m1 _
}

// 方法
//scala> def m1(a: Int, b: Int): Int = a * b
//m1: (a: Int, b: Int)Int
//+
//scala> m1(3, 5)
//res14: Int = 15
//
//scala> def m1(a: Int, b: Int) = a * b
//m1: (a: Int, b: Int)Int # 自动推导出Int类型

// 函数
//scala> (x: Int, y: Int) => {x + y}
//res0: (Int, Int) => Int = <function2>
//
//scala> res0(1, 2)
//res1: Int = 3
//scala> val f1 = (x: Int, y: Int) => {x + y}
//f1: (Int, Int) => Int = <function2>
//
//scala> f1(1, 2)
//res2: Int = 3

//scala> var arr = 1.to(10)
//arr: scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//
//scala> arr.map((x: Int) => x * 10)
//res3: scala.collection.immutable.IndexedSeq[Int] = Vector(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
//
//scala> arr.map(x => x * 100)
//res4: scala.collection.immutable.IndexedSeq[Int] = Vector(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000)
//
//scala> arr.map(_ * 100)
//res5: scala.collection.immutable.IndexedSeq[Int] = Vector(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000)

// 定义一个函数, 参数类型是Int, 返回一个元组(ActorSystem, Int), 后面的{}是函数体 (spark源码)
//val startService: Int => (ActorSystem, Int) = { actualPort =>
//doCreateActorSystem(name, host, actualPort, conf, securityManager)
//}
//Utils.startServiceOnPort(port, startService, conf, name)
//
//// 模拟
//val func: Int => String = { x => x.toString }
//
//val func1 = (x: Int) => x.toString
//
//
//scala> val func = (x: Int, y: Double) => (y, x)
//func: (Int, Double) => (Double, Int) = <function2>
//
//scala> func(1, 2.2)
//res103: (Double, Int) = (2.2,1)
//
////用最上面的一种方法实现函数的定义
//scala> val func2:(Int, Double) => (Double, Int) = {(x,y) => (y, x)}
//func2: (Int, Double) => (Double, Int) = <function2>
//
//scala> func2(2, 3.0)
//res104: (Double, Int) = (3.0,2)