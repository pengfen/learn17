package scala.basic

/**
  * 泛型
  * [T <: UpperBound]
  * [T >: LowerBound]
  * [T <% ViewBound]
  * [T : ContextBound]
  * [+T]
  * [-T]
  *
  * 视图界定
  * [T <% M]关系意味着T可以被隐式转换成M[T]
  * [T <% Comparable[T]]
  *
  * 上下文界定
  * [T : M]要求必须存在一个类型为M[T]的隐式值
  *
  * 在scala中泛型与java中的表示方式不同用"[]"
  * 例如[T <: Comparable[T]]表示上界，传入的为Comparable的子类
  */
class Pair[T <% Comparable[T]](val first: T, val second: T){
  def bigger = if(first.compareTo(second) > 0) first else second
}


object UpperBound {
  def main(args: Array[String]) {
    val p = new Pair(1, 5)
    println(p.bigger)
  }
}




