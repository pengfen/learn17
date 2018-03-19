package scala.basic

/**
  *
  * @param id
  * @param name
  * @param gender ---> private[this]
  * age var修饰时可以改 val修饰时只有构造器可以改
  */
class People(val id: String, var name: String, gender: String, var age: Int = 16) {

}

object People {
  def main(args: Array[String]): Unit = {
    val p = new People("666", "ricky", "f", 20)

    println(p.age)
    println(p.id)
    p.name = "caopeng"
    println(p.name)
  }
}
