package scala.basic

trait Animal {

  // def run() // 抽象方法

  def run(): Unit = { // 实现方法
    println("Animal run")
  }
}
