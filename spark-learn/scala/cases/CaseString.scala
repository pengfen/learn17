package scala.cases

import scala.util.Random

/**
  * scala有一个十分强大的模式匹配机制 可以应用到很多场合 如switch语句 类型检查
  *
  * scala提供了样例类 对模式匹配进行了优化 可以快速进行匹配
  *
  * 匹配字符串
  */
object CaseString extends App {

  val arr = Array("ricky", "peng", "caopeng")
  val name = arr(Random.nextInt(arr.length))
  println(name)
  name match {
    case "ricky" => println("ricky老师...")
    case "peng" => println("peng老师...")
    case _ => println("真不知道你们在说什么...")
  }

}
