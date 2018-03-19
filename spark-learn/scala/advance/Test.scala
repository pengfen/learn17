package scala.advance

/**
  *
  */
object Test {

  def main(args: Array[String]): Unit = {
    val b1 = new Boy("ricky", 99)
    val b2 = new Boy("peng", 999)

    val arr = Array(b1, b2)
    val sorted = arr.sortBy(x => x).reverse
    for (b <- sorted) {
      println(b.name)
    }
  }
}
