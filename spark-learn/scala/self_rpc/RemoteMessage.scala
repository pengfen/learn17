package scala.self_rpc

/**
  * 跨网络消息 (实现系列化)
  */
trait RemoteMessage extends Serializable

/**
  * Worker注册类 (Worker ---> Master)
  * @param id      worker_id
  * @param memory  内在大小
  * @param cores   核数
  */
case class RegisterWorker(id: String, memory: Int, cores: Int) extends RemoteMessage

/**
  * Worker注册类的反馈类 (Master ---> Worker)
  * @param masterUrl master_url
  */
case class RegisteredWorker(masterUrl: String) extends RemoteMessage

/**
  * Worker ---> Worker
  */
case object SendHeartBeat

/**
  * 心跳 Worker ---> Master
  * @param id  worker_id
  */
case class HeartBeat(id : String) extends RemoteMessage

/**
  * Master ---> self
  */
case object CheckTimeOutWorker