package scala.advance

/**
  * 柯里化
  * 将原来接受两个参数的函数编程接受一个参数的函数的过程
  * def fun(x: Int)(y: Int) = x * y
  * fun(6)(7)
  * fun(6) // (y: Int) => 6 * y
  * fun(7) // 42
  */
object FunDemo {
  def main(args: Array[String]) {
    def f2(x: Int) = x * 2

    val f3 = (x: Int) => x * 3
    val f4: (Int) => Int = { x => x * 4 }
    val f4a: (Int) => Int = _ * 4
    val f5 = (_: Int) * 5
    val list = List(1, 2, 3, 4, 5)
    var new_list: List[Int] = null
    //第一种：最直观的方式 (Int) => Int
    //new_list = list.map((x: Int) => x * 3)

    //第二种：由于map方法知道你会传入一个类型为(Int) => Int的函数，你可以简写
    //new_list = list.map((x) => x * 3)

    //第三种：对于只有一个参数的函数，你可以省去参数外围的()
    //new_list = list.map(x => x * 3)

    //第四种：(终极方式)如果参数在=>右侧只出现一次，可以使用_
    new_list = list.map(_ * 3)

    new_list.foreach(println(_))

    var a = Array(1,2,3)
    a.map(_* 3)
  }
}
