package spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import spark.utils.LoggerLevels
;

/**
  * Spark Streaming处理Socket数据
  *
  * 1. nc 的安装 sudo apt -y install netcat-traditional (centos yum install -y nc)
  *
  * 测试： nc -lk 33333 输入字符 (启动一个服务端并监听9999端口 nc -lk 9999)
  *
  * 查看控制台
  *
  * 注意错误 Restarting receiver with delay 2000 ms: Error connecting to localhost:33333
  *
  * 2. 编写代码
  *
  * 3. 启动Spark Streaming程序：由于使用的是本地模式"local[2]"所以可以直接在本地运行该程序
  * 注意：要指定并行度，如在本地运行设置setMaster("local[2]")，相当于启动两个线程，一个给receiver，一个给computer。
  * 如果是在集群中运行，必须要求集群中可用core数大于1
  *
  * 4.在Linux端命令行中输入单词
  *
  * 5.在IDEA控制台中查看结果
  */
object NetworkWordCount {

  // socket ---local[1])--- Receiver ---> Memory
  // 对于需要Receiver来接收数据的处理 那么本地测试时你的local[?]一定要大于1的
  def main(args: Array[String]): Unit = {
    // 设置日志级别
    LoggerLevels.setStreamingLogLevels()

    // 创建sparkConf并设置为本地模式运行
    // 注意local[2]代表开两个线程
    val sparkConf = new SparkConf().setAppName("NetworkWordCount").setMaster("local[2]")

    /**
      * 创建StreamingContext需要两个参数：SparkConf和batch interval
      */
    // 创建Spark Streaming Context，每隔5秒钟处理一批数据，那么这一秒收集的数据存放在哪，如何将收集的数据推送出去？
    // 是生产者主动推出去还是消费者每隔1秒钟来拉取一次数据
    // 设置DStream批次时间间隔为2秒
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    // 通过网络读取数据
    val lines = ssc.socketTextStream("localhost", 33333)

    // flatMap是把将每一行使用空格做分解，那么words对应的数据结构是怎么样的？
    // words是个集合，每个集合元素依然是个集合，这个集合存放单词
    val result = lines.flatMap(_.split(" ")) // 将读到的数据用空格切成单词
      .map((_,1)) // 将单词和1组成一个元组
      .reduceByKey(_+_) // 按单词进行分组求相同单词出现的次数

    result.print() // 打印结果到控制台

    // 启动计算作业
    ssc.start()
    // 等待结束，什么时候结束作业，即触发什么条件会让作业执行结束
    ssc.awaitTermination()
  }
}
