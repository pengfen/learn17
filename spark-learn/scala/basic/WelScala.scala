package scala.basic // package 声明包

import scala.actors.Actor._ // 导入Actor类的所有属性 方法

class Group private(val name:String) { // 类与私有主构造函数声明
  def say(msg:String) = { // 定义类方法成员
    println("The Group" + this.name + "say : " + msg) // 控制台输出及字符串拼接
  }

  def play(game:String, members:Array[Member], perform: Member => String) = { // 接受函数值的类方法
    println("The group is playing a game : " + game)
    members.foreach{
      member =>
        val result = perform(member) // val 声明常量
        if (result != null) println(result) // if 条件判断语句
    }
  }

  def play_curry(game:String, members:Array[Member])(perform:Member => String) = { // curry化函数定义 fun(a)(b)(c)
    this.play(game, members, perform)
  }

  def search(condition:Any): String = { //　接受任意多个任意类型参数
    Thread.sleep(2000) // 线程睡眠
    return "the result is : their are too many people named jeff!"
  }
}

object Group { // group的伴生对象
  private val groups = Map( // 创建字典对象
    "techparty" -> new Group("techparty"),
    "barcamp" -> new Group("barcamp")
  )

  def getGroup(name: String) = {
    if (groups.contains(name)) groups(name) else null
  }
}

class Member(val name: String, val topic: String) extends Fan { // 混入Fan特性
  def this(name:String) { // 副构造函数
    this(name, null) // 任何副构造函数均应调用主构造函数
  }
}

trait Fan { // 定义特性(trait)
  def like() = "techparty"
}

trait JavaFan extends Fan { // 扩展特性
  override def like(): String = "java," + super.like()
}

trait PythonFan extends Fan {
  override def like(): String = "python," + super.like()
}

object WelScala {
  def main(args: Array[String]): Unit = { // 程序入口
    val techparty = Group.getGroup("techparty")
    techparty say "welcome to scala world" // DSL风格的函数调用

    // 创建数组
    val members = Array( // 运行时混入特性 可混入多个
      new Member("peng", "welcome") with PythonFan,
      new Member("ricky", "love learn") with JavaFan with PythonFan,
      new Member("caopeng")
    )
    techparty.play("love self", members, member => "My name is " + member.name + " and, I like " + member.like())

    def present(member: Member) = {
      if (member.topic != null) member.name + " is presenting " else null
    }
    techparty.play("present the topic", members, present) // 以函数作为参数调用函数

    val potluck = techparty.play("let's go potluck", members, _: Member => String) // _ 偏应用函数的定义及使用
    potluck(_.name + "is eating")

    techparty.play_curry("dismiss", members) { // 函数的curry化
      member => member.name + "is leaving and going home"
    }

    val receiver = self // 把自己变成一个actor self不是this
    actor { // 创建一个Actor
      receiver ! techparty.search("ricky", 2018) // 给另一个actor异步发送消息
    }

    val jane = actor {
      receiver ! (self, techparty.search("peng")) // 创建另一个Actor 可保留对其引用
    }

    for (i <- 1 to 2) { // for循环
      receive { // 同步接收消息
        case (at, status: String) => println("he, jane have her searching job done!")
        case result: String => println("search result: " + result) // 模式匹配
      }
    }
  }
}
