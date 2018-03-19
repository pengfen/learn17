package scala.basic

/**
  * ctrl + i 找到没有实现的方法
  *
  * 扩展类 ---> 在scala中扩展类的方式和Java一样都是使用extends关键字
  *
  * 重写方法 ---> 在scala中重定一个非抽象的方法必须使用override修饰符
  *
  * 类型检查和转换
  * scala  obj.isInstanceOf[C] ---> java  obj instanceof C
  * scala  obj.asInstanceOf[C] ---> java  (C)obj
  * scala  classOf[C]  ---> java C.class
  *
  */
class Chinese extends Human with Animal with Monkey with Flyable {
  val name = "abc"

  //打印几次"ABC"?
  val t1,t2,(a, b, c) = {
    println("ABC")
    (1,2,3)
  }

  println(a)
  println(t1._1)

  //在子类中重写超类的抽象方法时，不需要使用override关键字，写了也可以
  override def run(): Unit = {
    println("run")
  }

  //在Scala中重写一个非抽象方法必须用override修饰
  override def fight(): String = {
    "fight with 棒子"
  }

}

object Chinese {
  def main(args: Array[String]): Unit = {
    val c = new Chinese
    c.run()
  }
}
