package scala.basic

import kafka.utils.Logging
import org.apache.hadoop.util.SignalLogger
import org.apache.spark.SparkConf

object Master {}
//private[spark] object Master extends Logging{
//  val systemName = "sparkMaster"
//  private val actorName = "Master"
//
//  def main(args: Array[String]): Unit = {
//    SignalLogger.register(log)
//    val conf = new SparkConf()
//    val args = new MasterArguments(argStrings, conf)
//    val (actorSystem, _, _, _) = startSystemAndActor(args.host, args.port, args.webUiPort, conf)
//    actorSystem.awaitTermination()
//  }
//}
