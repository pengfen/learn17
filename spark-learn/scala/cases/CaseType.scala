package scala.cases

import scala.util.Random

/**
  * 匹配类型
  */
object CaseType extends App{

  //val x = 3
  //val v = if(x >= 5) 1 else if(x < 2) 2.0 else "hello"
  val arr = Array("hello", 1, -2.0, CaseType)
  val elem = arr(Random.nextInt(arr.length))

  println(elem)

  // 注意 case y: Double if (y >= 0) => ...
  // 模式匹配的时候可以添加守卫条件 如不符合守卫条件 将掉入case _中
  elem match {
    case x: Int => println("Int " + x)
    case y: Double if(y >= 0) => println("Double "+ y)
    case z: String => println("String " + z)
    case _ => throw new Exception("not match exception")
  }

}