package scala.cases

/**
  * 在scala中option类型样例类用来表示可能存在　也可能不存在的值
  * Option的子类有Some和None
  * Some包装了某个值
  * None表示没有值
  */
object OptionDemo {
  def main(args: Array[String]) {
    val map = Map("a" -> 1, "b" -> 2)

    val v = map.get("b") match {
      case Some(i) => i
      case None => 0
    }
    println(v)

    //更好的方式
    val v1 = map.getOrElse("c", 0)
    println(v1)
  }
}
