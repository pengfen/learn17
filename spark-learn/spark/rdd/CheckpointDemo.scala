package spark.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
  * checkpoint的意思就是建立检查点,类似于快照,例如在spark计算里面 计算流程DAG特别长,服务器需要将整个DAG计算完成得出结果,
  * 但是如果在这很长的计算流程中突然中间算出的数据丢失了,spark又会根据RDD的依赖关系从头到尾计算一遍,这样子就很费性能,
  * 当然我们可以将中间的计算结果通过cache或者persist放到内存或者磁盘中,但是这样也不能保证数据完全不会丢失,
  * 存储的这个内存出问题了或者磁盘坏了,也会导致spark从头再根据RDD计算一遍,所以就有了checkpoint,
  * 其中checkpoint的作用就是将DAG中比较重要的中间数据做一个检查点将结果存储到一个高可用的地方(通常这个地方就是HDFS里面)
  */
class CheckpointDemo {

  def main(args: Array[String]): Unit = {
    // spark 集群的入口
    val conf = new SparkConf()

    // A master URL must be set in your configuration 本地测试时如果没有配置master会出现此错误
    conf.setAppName("CheckpointDemo").setMaster("local[2]")

    val sc = new SparkContext(conf)
    sc.setCheckpointDir(".");

    val in = "hdfs://ricky:9000/wc.txt"
    // 注意 1. cd $HADOOP_HOME ---> sbin/start-dfs.sh hdfs已启动
    // 2. out目录不存在 hadoop fs -rmr /out
    val out = "hdfs://ricky:9000/out"

    sc.textFile(in).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).sortBy(_._2, false).saveAsTextFile(out)

    sc.stop()
  }
}
