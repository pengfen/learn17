package scala.advance

class Boy(val name: String, val faceValue: Int) extends Comparable[Boy]{
  override def compareTo(o: Boy): Int = {
    this.faceValue - o.faceValue
  }
}
