package scala.basic

/**
  * 类
  * var 带getter和setter属性
  * val 只带getter属性
  * private和private[this]
  * 主构造器和辅助构造器
  * 跟在类名后面的是主构造器
  * 主构造器执行类定义中的所有语句
  * 辅助构造器的名称为this
  * 每一个辅助构造器必须先调用主构造器或其他已经定义好的辅助构造器
  * object对象
  * 用对象作为单例或存放工具方法
  * 可作为伴生对象
  * 用类的伴生对象的apply方法创建新的实例
  * 扩展App特质作为main方法使用
  * 包
  * 包可见性private[spark]
  * 重命名import java.util.{HashMap => JHashMap}
  * 隐式导入 和Java程序一样java.lang总是被默认引入 scala也默认引入
  * 继承
  * 重写方法 重写一个非抽象的方法必须使用override修饰符
  * o.isInstanceOf[CI] //java中的instanceof
  * o.asInstacnceOf[CI] //java中的(CI) o
  * classOf[CI] //java中的CI.class
  *
  * 在 scala 中 类并不用声明为public
  *
  * scala 源文件中可以包含多个类 所有这些类都具有公有可见性
  *
  * 包可见性 private[scala] class Person ---> scala 包下可见
  *
  * 私有构造器 (只有伴生对象可以访问) class Person privete {}
  */
class Person {
  // 用val修饰的变量是只读属性 有getter但没有setter (相当与java中用final修饰的变量)
  val id = "66688" // val 修饰的只有get方法 (getId)
  // 用var修饰的变量既有getter又有setter ---> 类私有字段 只能在类的内部使用
  var name = "caopeng" // var 修饰的只有get方法和set方法 (getName setName)
  private var gender: String = "male" // 只能在伴生对象中使用
  private[this] var pop: String = _ // (_表示未初始化)

  def printPop: Unit = {
    println(pop)
  }

  // 对象私有字段 访问权限更加严格的 Person类的方法只能访问到当前对象的字段
  private[this] val pet = "小强"
}

// 伴生对象
object Person {
  def main(args: Array[String]): Unit = {
    val p = new Person
    println(p.id + " " + p.name)

    p.name = "ricky"
    println(p.id + " " + p.name)

    println(p.gender)
    p.gender = "男"
    println(p.gender)

    p.printPop
  }
}
