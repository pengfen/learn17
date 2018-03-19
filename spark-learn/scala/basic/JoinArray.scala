package scala.basic

/**
  * 拉链操作
  *
  * zip 命令可以将多个值绑定在一起
 */
object JoinArray {
  def main(args: Array[String]): Unit = {
    //    scala> val scores = Map("tom" -> 80, "jerry" -> 90)
    //    scores: scala.collection.mutable.Map[String,Int] = Map(tom -> 80, jerry -> 90)
    //
    //    scala> val names = Array(88, 95, 80)
    //    names: Array[Int] = Array(88, 95, 80)
    //
    //    scala> val ns = names.zip(scores) // 使用zip将对应的值绑定到一起
    //    ns: Array[(Int, (String, Int))] = Array((88,(tom,80)), (95,(jerry,90)))
    //
    //    scala> ns.toMap
    //    res11: scala.collection.immutable.Map[Int,(String, Int)] = Map(88 -> (tom,80), 95 -> (jerry,90))
  }
}

//scala> val a = Array("a", "b", "c")
//a: Array[String] = Array(a, b, c)
//
//scala> val b = Array(1, 2, 3)
//b: Array[Int] = Array(1, 2, 3)
//
//scala> a.zip(b)
//res37: Array[(String, Int)] = Array((a,1), (b,2), (c,3))
//
//scala> res37.toMap
//res38: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2, c -> 3)

//scala> val b = Array(1, 2, 3, 4)
//b: Array[Int] = Array(1, 2, 3, 4)
// 注意　如果两个数组的元素个数不一致 拉链操作后生成的数组的长度为较小的那个数组的元素个数
//scala> a.zip(b)
//res39: Array[(String, Int)] = Array((a,1), (b,2), (c,3))
