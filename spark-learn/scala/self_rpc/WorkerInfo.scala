package scala.self_rpc

/**
  * Worker信息
  */
class WorkerInfo(val id: String, val memory: Int, val cores: Int) {

  // 上一次心跳
  var lastHeartbeat : Long = _
}
