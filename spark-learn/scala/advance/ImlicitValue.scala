package scala.advance

/**
  * 隐式转换和隐式参数
  * 隐式转换和隐式参数是scala中两个功能强大的工具　利用隐式转换可以丰富现有类的功能　从而写出非常优雅的程序
  * 利用隐式转换丰富类库功能
  * val contents = new File("/home/ricky/data/scala/words.txt").read
  * 引入隐式转换
  * 位于源或目标类型的伴生对象中的隐式函数
  * 位于当前作用域可以以单个标识符指代的隐式函数
  * 隐式参数
  * 方法中带有以一个标识为implict的参数列表　编译器会查找缺省值　提供给该方法
  * 被查找到的值必须被implict修饰并与方法中implict修饰的类型相同
  * 利用隐式参数进行隐式转换
  * 参数必须是一个单参数的函数 并且使用implict修饰该参数
  *
  * 隐式转换
  *
  * 隐式转换和隐式参数是scala中两个非常强大的功能　利用隐式转换和隐式参数 可以提供优雅的类库　对类库的使用隐匿掉那些枯燥乏味的结节
  *
  * 作用 隐式的对类的方法进行增强　丰富现有类库的功能
  *
  * 以implicit关键字声明的带有单个参数的函数
  *
  *
  * 所有的隐式值和隐式方法必须放到object
  */
object Context {
  implicit val aaa = "ricky"
}

object ImlicitValue {
  def sayWel()(implicit name: String = "caopeng"): Unit = {
    println(s"welcome $name")
  }

  def main(args: Array[String]): Unit = {
    import Context._
    sayWel() // 不加隐式转换 welcome caopeng  ---> 添加隐式转换 welcome ricky
  }
}
