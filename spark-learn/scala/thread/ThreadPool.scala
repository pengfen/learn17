package scala.thread

import java.util.concurrent.{ExecutorService, Executors}

/**
  * 线程池
  */
object ThreadPool {

  def main(args: Array[String]): Unit = {
    // ExecutorService pool = Executors.newCachedThreadPool();
    val pool = Executors.newCachedThreadPool();
    // for (int i = 1; i <= 10; i++)
    for (i <- 1 to 10) {
      pool.execute(new Runnable {
        // public void run()
        def run(): Unit = {
          println(Thread.currentThread().getName)
          Thread.sleep(1000)
        }
      })
    }

    println("----------------------------->")
    for (i <- 1 to 15) {
      pool.execute(new Runnable {
        // public void run()
        def run(): Unit = {
          println(Thread.currentThread().getName)
          Thread.sleep(1000)
        }
      })
    }
    pool.shutdown()
  }
}
