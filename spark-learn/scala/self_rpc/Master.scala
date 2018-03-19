package scala.self_rpc

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.duration._

/**
  * Master为整个集群中的主节点 ---> Master继承了Actor
  *
  * @param host Master ip地址
  * @param port Master 端口号
  */
class Master(val host: String, val port: Int) extends Actor{

  //println("constructor invoked")
  // workerId ---> WorkerInfo 保存WorkerID和Work信息的map
  val idToWorker = new mutable.HashMap[String, WorkerInfo]()
  // WorkerInfo 保存所有Worker信息的Set
  val workers = new mutable.HashSet[WorkerInfo]()
  //Worker超时时间 (检测心跳间隔时间10s)
  val WORKER_TIMEOUT = 15 * 1000
  //重新receive方法
  import context.dispatcher

  override def preStart(): Unit = { //构造方法执行完执行一次
    println("preStart invoked")
    // 导入隐式转换
    import context.dispatcher

    //启动定时器，定时执行
    context.system.scheduler.schedule(0 millis, WORKER_TIMEOUT millis, self, CheckTimeOutWorker)
  }

  //该方法会被反复执行，用于接收消息，通过case class模式匹配接收消息
  override def receive: Receive = {
    // Worker向Master发送的注册消息
    case RegisterWorker(id, memory, cores) => {
      // 判断是否已经注册过
      if (!idToWorker.contains(id)) {
        // 如果没有注册　就把Worker的信息封装起来保存到内存当中
        val workerInfo = new WorkerInfo(id, memory, cores)
        idToWorker(id) = workerInfo
        workers += workerInfo
        sender ! RegisteredWorker(s"akka.tcp://MasterSystem@$host:$port/user/Master")
        //sender ! RegisteredWorker("192.168.10.1")
      }
    }

    //Worker向Master发送的心跳消息
    case HeartBeat(id) => { // 处理worker发送的心跳信息
      if (idToWorker.contains(id)) {
        val workerInfo = idToWorker(id)
        // 报活
        val curentTime = System.currentTimeMillis()
        // 设置最近一次心跳时间
        workerInfo.lastHeartbeat = curentTime
      }

    }

    //Master自己向自己发送的定期检查超时Worker的消息
    case CheckTimeOutWorker => {
      val currentTime = System.currentTimeMillis()
      val toRemove = workers.filter(w => currentTime - w.lastHeartbeat > WORKER_TIMEOUT).toArray
      for(worker <- toRemove){
        workers -= worker
        idToWorker.remove(worker.id)
        //idToWorker -= worker.id
      }
      println("worker size: " + workers.size)
    }
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
    val host = "127.0.0.1"
    val port = 9991
//    if (args.length != 2) {
//      // 127.0.0.1 9991
//      System.err.println("Usage: host port ")
//      System.exit(1)
//    }
//
//    val Array(host, port) = args

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
    //val master = actorSystem.actorOf(Props[Master], "Master") // Master主构造器会执行
    val master = actorSystem.actorOf(Props(new Master(host, port)), "Master")
    //master ! "wel" // 发送信息
    actorSystem.awaitTermination() // 让进程等待
  }
}


