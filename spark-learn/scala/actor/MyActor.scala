package scala.actor

import scala.actors.Actor

/**
  * 在act()方法中加入了while(true)循环　就可以不停的接收消息
  *
  * 注意 发送start消息和stop的消息是异步的 但是Actor接收到消息执行的过程是同步的按顺序执行
  *
  */
class MyActor extends Actor {

  override def act(): Unit = {
    while (true) {
      receive {
        case "start" => {
          println("starting ...")
          Thread.sleep(5000)
          println(Thread.currentThread().getName)
          println("started")
        }
        case "stop" => {
          println("stopping ...")
          //Thread.sleep(8000)
          println(Thread.currentThread().getName)
          println("stopped ...")
        }
      }
    }
  }
}

// react方式会复用线程　比receive更高效
// react如果要反复执行消息处理　react外层要用loop　不能用while
class YourActor extends Actor {
  override def act(): Unit = {
    loop {
      react {
        case "start" => {
          println("starting ...")
          Thread.sleep(5000)
          println(Thread.currentThread().getName)
          println("started")
        }
        case "stop" => {
          println("stopping ...")
          Thread.sleep(8000)
          println(Thread.currentThread().getName)
          println("stopped ...")
        }
        case "exit" => {
          exit()
        }
      }
    }
  }
}


object Boot {

  def main(args: Array[String]) {
    // 没有返回值的异步消息
    val actor = new MyActor
//    actor.start()
//    actor ! "start"
//    actor ! "stop"
//    println("消息发送完成！")
//
//    actor ! "stop"

    val a2 = new YourActor
    a2.start()
    a2 ! "start"
    a2 ! "stop"
    Thread.sleep(10000)
    a2 ! "exit"

//    val a3 = new YourActor
//    a3.start()
//    a3 ! "start"

  }
}
