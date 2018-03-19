package scala.rpc

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class Worker(val masterHost: String, val masterPort: Int) extends Actor{

  var master : ActorSelection = _
  // 建立连接
  override def preStart(): Unit = {
    //在master启动时会打印下面的那个协议, 可以先用这个做一个标志, 连接哪个master
    //继承actor后会有一个context, 可以通过它来连接
    //master = context.actorSelection("akka.tcp://MasterSystem@127.0.0.1:9991/user/Master")
    master = context.actorSelection(s"akka.tcp://MasterSystem@$masterHost:$masterPort/user/Master") // 注意　需要有/user
    master ! "connect" // 给master发消息
  }

  override def receive: Receive = {
    case "reply" => {
      println("a reply from master")
    }
  }
}

object Worker {
  def main(args: Array[String]): Unit = {
    val host = "127.0.0.1"
    val port = 9992
    val masterHost = "127.0.0.1"
    val masterPort = 9991

//    if (args.length != 4) {
//      // 127.0.0.1 9992 127.0.0.1 9991
//      System.err.println("Usage: host port masterHost masterPort")
//      System.exit(1)
//    }
//
//    val Array(host, port, masterHost, masterPort) = args

    //创建ActorSystem的必要参数
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)

    // ActorSystem老大　辅助创建和监控下面的Actor 单例的
    val actorSystem = ActorSystem("WorkerSystem", config)
    //actorSystem.actorOf(Props[Worker], "Worker")
    actorSystem.actorOf(Props(new Worker(masterHost, masterPort)), "Worker")
    actorSystem.awaitTermination()
  }
}