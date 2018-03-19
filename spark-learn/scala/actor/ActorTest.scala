package scala.actor

import scala.actors.Actor // 注意导包是 scala.actors.Actor

/**
  * Scala Actor并发编程 ---> 初识Actor
  *
  * 分别调用了两个单例对象的start()方法　他们的act()方法会被执行
  * 相当于在java中开启丙个线程　线程的run()方法会被执行
  *
  * 注意　两个Actor是并行执行的 act()方法中的for循环执行完成后actor程序就退出了
  *
  */
object MyActor1 extends Actor{
  //重新act方法
  def act(){
    for(i <- 1 to 20){
      println("actor-1 " + i)
      Thread.sleep(1000)
    }
  }
}

object MyActor2 extends Actor{
  //重新act方法
  def act(){
    for(i <- 1 to 20){
      println("actor-2 " + i)
      Thread.sleep(1000)
    }
  }
}

object ActorTest extends App{
  //启动Actor
  MyActor1.start()
  MyActor2.start()
  println("main")
}


