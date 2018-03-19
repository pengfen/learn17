package scala.self_rpc

import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._

/**
  * Worker为整个集群的从节点 ---> Worker继承了Actor
  * @param masterHost Master节点 IP
  * @param masterPort Master节点端口号
  * @param memory 内存大小
  * @param cores 核数
  */
class Worker(val masterHost: String, val masterPort: Int, val memory: Int, val cores: Int) extends Actor{

  // Worker端持有Master端的引用（代理对象）
  var master : ActorSelection = _
  // 生成一个UUID，作为Worker的标识
  val workerId = UUID.randomUUID().toString
  // 发送心跳的时间间隔
  val HEART_INTERVAL = 10000

  // 建立连接 ---> 构造方法执行完执行一次
  override def preStart(): Unit = {
    //在master启动时会打印下面的那个协议, 可以先用这个做一个标志, 连接哪个master
    //继承actor后会有一个context, 可以通过它来连接
    //Worker向MasterActorSystem发送建立连接请求
    //master = context.actorSelection("akka.tcp://MasterSystem@127.0.0.1:9991/user/Master")
    master = context.actorSelection(s"akka.tcp://MasterSystem@$masterHost:$masterPort/user/Master") // 注意　需要有/user
    //master ! "connect" // 给master发消息
    // Worker向Master发送注册消息
    master ! RegisterWorker(workerId, memory, cores)
  }

  //该方法会被反复执行，用于接收消息，通过case class模式匹配接收消息
  override def receive: Receive = {
    //Master向Worker的反馈信息
    case RegisteredWorker(masterUrl) => { // 处理master_url
      println(masterUrl)
      // 定时发送心跳 ---> 启动定时器发送心跳
      import context.dispatcher
      //启动定时任务，向Master发送心跳+
      context.system.scheduler.schedule(0 millis, HEART_INTERVAL millis, self, SendHeartBeat)
    }

    case SendHeartBeat => {
      println("send heartbeat to master")
      master ! HeartBeat(workerId)
    }

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
    val memory = 1024
    val cores = 1

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
    actorSystem.actorOf(Props(new Worker(masterHost, masterPort, memory, cores)), "Worker")
    actorSystem.awaitTermination()
  }
}
