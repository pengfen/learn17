package spark.rdd

/**
  * 测试hash值
  *
  * 模数mod值越大 产生hash碰撞越小
  *
  * 测试不同的key(spark, java, php)的partNum值
  */
object TestHash {

  def main(args: Array[String]): Unit = {
    val key = "java.cn"
    val mod = 100
    val rawMod = key.hashCode % mod
    val partNum = rawMod + (if (rawMod < 0) mod else 0)
    println(partNum)
  }
}
