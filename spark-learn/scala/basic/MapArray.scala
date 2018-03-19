package scala.basic

/**
  * 数组常用算法
  * val arr = Array(4, 3, 5, 1, 2)
  * val res = arr.sum
  * Array("spark", "hadoop", "storm").min
  * val b = arr.sorted
  * val c = arr.sortWith(_>_)
  * val d = arr.count(_>2)
  *
  * 映射
  * val map = Map("a" -> 1, "b" -> 2, "C" -> 3)或val map = Map(("a", 1), ("b", 2), ("c", 3))
  * // 取值　赋值
  * map("a")
  * map.getOrElse("d", 0)
  * map("b") = 22
  * map += ("e" -> 8, "f" -> 9)
  * map -= ("f")
  * // 迭代
  * for ((k, v) <- map)
  * // 交换k,v
  * for ((k, v) <- map) yield (v, k)
  * //keySet和values
  *
  *
  * 映射 Map
  *
  * 注意 scala中 有两种 Map (一个是immutable包下的Map 该Map中的内容不可变   另一个是mutable包下的Map 该Map中的内容可变)
  *
  * 注意　通常创建一个集合是会用val这个关键字修饰一个变量(相当于java中的final)
  * 意味着该变量的引用不可变 该引用中的内容是不是可变 取决天这个引用指向的集合的类型
  *
  */
object MapArray {
  def main(args: Array[String]): Unit = {
    // 构建映射
    // 1. 使用箭头构建map
    val scores = Map("tom" -> 85, "jerry" -> 99, "kitty" -> 90) // 使用箭头
    //scores: scala.collection.immutable.Map[String,Int] = Map(tom -> 85, jerry -> 99, kitty -> 90)
    println(scores)

    // 2. 使用元组构建map
    val scores_meta = Map(("tom", 85), ("jerry", 99), ("kitty", 90)) // 使用元组
    // scores: scala.collection.immutable.Map[String,Int] = Map(tom -> 85, jerry -> 99, kitty -> 90)
    println(scores_meta)

    val value = scores("jerry") // 获取映射中的值
    println(value)
    //    res4: Int = 99

    // 如果映射中有值 返回映射中的值 没有返回默认值
    val suke = scores.getOrElse("suke", 0)
    //    res5: Int = 0
    println(suke)
    //
    //    scala> scores.getOrElse("tom", 0)
    //    res6: Int = 85

    // 在Scala中 有两种Map 一个是immutable包下的Map 该Map中的内容不可变 另一个是mutable包下的Map 该Map中的内容可变
    //    scala> val scores = Map("tom" -> 80, "jerry" -> 90) // 导入mutable包
    //    scores: scala.collection.mutable.Map[String,Int] = Map(tom -> 80, jerry -> 90) // val定义的scores变量意味着引用不变 但是Map中的内容可变
    //
    //    scala> scores("tom") - 88 // 修改Map中的内容
    //    res7: Int = -8
    //
    //    scala> scores += ("kitty" -> 99, "suke" -> 60) // 用+=向原来的Map中追加元素
    //    res9: scores.type = Map(tom -> 80, kitty -> 99, jerry -> 90, suke -> 60)
  }
}

//scala> val m = Map("a" -> 1, "b" -> 2)
//m: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2)
//
//scala> m("a")
//res20: Int = 1

// 可变Map
//scala> import scala.collection.mutable.Map
//import scala.collection.mutable.Map
//
//scala> val mm = Map("i" -> 1, "j" -> 2)
//mm: scala.collection.mutable.Map[String,Int] = Map(j -> 2, i -> 1)
//
//scala> mm("i")
//res21: Int = 1
//
//scala> mm("i") = 10
//
//scala> mm("k") = 20
//
//scala> mm
//res24: scala.collection.mutable.Map[String,Int] = Map(k -> 20, j -> 2, i -> 10)

//scala> mm += ("o" -> 6)
//res25: mm.type = Map(k -> 20, j -> 2, i -> 10, o -> 6)
//
//scala> mm += (("o", 7))
//res26: mm.type = Map(k -> 20, j -> 2, i -> 10, o -> 7)

// 使用java map
//scala> import java.util.HashMap
//import java.util.HashMap
//
//scala> val hm = new HashMap()
//hm: java.util.HashMap[Nothing,Nothing] = {}

//scala> val m = Map(("a", 1), ("b", 2))
//m: scala.collection.mutable.Map[String,Int] = Map(b -> 2, a -> 1)
//
//scala> m("a") = 10
//
//scala> m
//res28: scala.collection.mutable.Map[String,Int] = Map(b -> 2, a -> 10)
// 找不到就使用默认值
//scala> m.getOrElse("c", 0)
//res29: Int = 0

// 元组下标是从１开始
//scala> val t = (1, "spark", 2.0)
//t: (Int, String, Double) = (1,spark,2.0)
//
//scala> t._2
//res30: String = spark
//
//scala> val r = t._2
//r: String = spark

//scala> val pair = ("t", 5)
//pair: (String, Int) = (t,5)
//
//scala> m
//res31: scala.collection.mutable.Map[String,Int] = Map(b -> 2, a -> 10)
//
//scala> m += pair
//res32: m.type = Map(t -> 5, b -> 2, a -> 10)
//
//scala> m += (("y", 6), ("z", 3))
//res33: m.type = Map(z -> 3, t -> 5, b -> 2, y -> 6, a -> 10)

//scala> val t = ("a", 1, 2.0)
//t: (String, Int, Double) = (a,1,2.0)
//
//scala> t._2
//res34: Int = 1
//
//scala> val t, (x, y, z) = ("a", 1, 2.0)
//t: (String, Int, Double) = (a,1,2.0)
//x: String = a
//y: Int = 1
//z: Double = 2.0
//
//scala> x
//res35: String = a

//scala> val arr = Array(("a", 1), ("b", 2))
//arr: Array[(String, Int)] = Array((a,1), (b,2))
//
//scala> arr.toMap
//res36: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2)
