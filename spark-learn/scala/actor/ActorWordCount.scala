package scala.actor

import java.io.File

import scala.actors.{Actor, Future}
import scala.collection.mutable.{HashSet, ListBuffer}
import scala.io.Source

/**
  * 使用actor并发编程写一个单机版的WorldCount
  *
  * 将多个文件作为输入　计算完成后将多个任务汇总　得到最终的结果
  *
  */
class Task extends Actor {
  override def act(): Unit = {
    loop {
      react {
        case SubmitTask(filename) => {
          //val lines = Source.fromFile(new File(filename)).getLines().toList
          //val result = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.length)
          //val result = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.foldLeft(0)(_ + _._2))
          //局部统计, 结果是Map[String, Int]
          val result = Source.fromFile(filename).getLines().flatMap(_.split(" ")).map((_, 1)).toList
            .groupBy(_._1).mapValues(_.size)
          // println(result) ---> Map(tom -> 2, jerry -> 1, hello -> 3)
          sender ! ResultTask(result) //发送ResultTask, 用它来包装result
        }
        case StopTask => {
          exit()
        }
      }
    }
  }
}

case class SubmitTask(filename: String)
case class ResultTask(reslut : Map[String, Int])
case object StopTask

object ActorWordCount {

  def main(args: Array[String]) {
    val replySet = new HashSet[Future[Any]]()
    val resultList = new ListBuffer[ResultTask]()

    val files = Array[String]("/home/ricky/data/scala/words.txt", "/home/ricky/data/scala/words.log")
    for (f <- files) {
      println(f)
      val actor = new Task
      val reply = actor.start() !! SubmitTask(f)  //启动, 并发送消息,返回Future ---> <function0>
      // println(reply) ---> <function0>
      replySet += reply //把这些Future放到集合中
    }
    // println(replySet) ---> Set(<function0>, <function0>)

    while(replySet.size > 0) {
      val toCompute = replySet.filter(_.isSet)  //取出有效的结果, 待处理的数据
      //println(toCompute)
      for(f <- toCompute) {
        val result = f().asInstanceOf[ResultTask] //获取实例, 注意f后要加(), 调用apply(), 否则会报转换异常
        // println(result) ---> ResultTask(Map(tom -> 2, jerry -> 1, hello -> 3))
        resultList += result
        //println(resultList) ---> ListBuffer(ResultTask(Map(tom -> 2, jerry -> 1, hello -> 3)), ResultTask(Map(tom -> 2, jerry -> 1, hello -> 3)))
        replySet -= f
      }
      Thread.sleep(100)
    }

    //汇总的功能
    //List((hello, 5), (tom,3), (helllo, 2), (jerry, 2))
    val fr = resultList.flatMap(_.reslut).groupBy(_._1).mapValues(_.foldLeft(0)(_+_._2))
    println(fr) // Map(tom -> 4, jerry -> 2, hello -> 6)
    //val finalResult = resultList.map(_.result).flatten.groupBy(_._1).mapValues(x => x.foldLeft(0)(_ + _._2))
    //println(finalResult)

  }
}
