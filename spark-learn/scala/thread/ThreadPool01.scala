package scala.thread

import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

/**
  * 测试线程池
  */
object ThreadPool01 {

  def main(args: Array[String]) {
    val threadPool: ExecutorService = Executors.newFixedThreadPool(10)
    val task = new Runnable {
      override def run(): Unit = {
        println(Thread.currentThread().getName)
        println("sub:--->submitting task...")
        Thread.sleep(1000)
        println("sub:--->task finished")
      }
    }
    println(Thread.currentThread().getName)
    println("main:----pre")
    threadPool.execute(task)
    println("main:----post")

    //threadPool.shutdown()
    threadPool.shutdownNow()
  }

}
