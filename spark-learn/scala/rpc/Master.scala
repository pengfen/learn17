package scala.rpc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class Master extends Actor{

  println("constructor invoked")

  override def preStart(): Unit = {
    println("preStart invoked")
  }

  // 用于接收消息
  override def receive: Receive = {
    case "connect" => {
      println("a client connected")
      sender ! "reply" // 反馈消息
    }

    case "wel" => {
      println("welcome to akka world")
    }
  }
}

object Master {
  def main(args: Array[String]): Unit = {
    //val host = "127.0.0.1"
    //val port = 9991
    if (args.length != 2) {
      // 127.0.0.1 9991
      System.err.println("Usage: host port ")
      System.exit(1)
    }

    val Array(host, port) = args

    //创建ActorSystem的必要参数
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)

    // ActorSystem老大　辅助创建和监控下面的Actor 单例的
    val actorSystem = ActorSystem("MasterSystem", config)

    // 创建Actor
    //启动Actor，Master会被实例化，生命周期方法会被调用
    val master = actorSystem.actorOf(Props[Master], "Master") // Master主构造器会执行
    master ! "wel" // 发送信息
    actorSystem.awaitTermination() // 让进程等待
  }
}
