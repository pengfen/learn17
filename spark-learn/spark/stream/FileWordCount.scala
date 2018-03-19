package spark.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
;

/**
  * 使用Spark Streaming处理文件系统(local/hdfs)的数据
  *
添加数据
ricky@ricky:~/data$ cat test.txt
a b c c b a d
ricky@ricky:~/data$ cp test.txt data/
ricky@ricky:~/data$ cp test.txt data/1
ricky@ricky:~/data$ cp test.txt data/2

查看控制台
(d,1)
(a,2)
(b,2)
(c,2)
  */
object FileWordCount {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local").setAppName("FileWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val lines = ssc.textFileStream("file:///home/ricky/data/spark_stream")

    val result = lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    result.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
