package scala.advance

class Chooser[T <% Ordered[T]] {

  def choose(first: T, second: T): T = {
    if (first > second) first else second
  }
}

object Chooser {

  def main(args: Array[String]): Unit = {
    import MyPreDef._
    val c = new Chooser[Girl]
    val g1 = new Girl("ricky", 90, 20)
    val g2 = new Girl("caopeng", 20, 20)

    val g = c.choose(g1, g2)
    println(g.name)
  }
}