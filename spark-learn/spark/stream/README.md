spark streaming 开发

1. 添加依赖 (参看 flow.html)

2. 编写 NetworkWordCount (spark streaming处理socket数据)
word_count.sh
spark-shell --master local[2]
注意 配置hive-site.html后 需要加mysql驱动包
spark-shell --master local[2] --jars /home/ricky/software/mysql-connector-java-5.1.27-bin.jar

import org.apache.spark.streaming.{Seconds, StreamingContext}

val ssc = new StreamingContext(sc, Seconds(1))
val lines = ssc.socketTextStream("ricky", 9999)
val words = lines.flatMap(_.split(" "))
val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
wordCounts.print()
ssc.start()
ssc.awaitTermination()

3. 编写 FileWordCount (spark streaming处理HDFS文件数据)

4. 编写 StatefulWordCount (使用Spark Streaming完成有状态统计)

5. 编写 ForeachRDDApp (计算到目前为止累积出现的单词个数写入到MySQL)

6. 编写 TransformApp (黑名单过滤)

7. 编写 SqlNetworkWordCount (spark streaming 整合 spark sql 实战)

8. 编写 WindowOpts(窗口函数)

9. 编写 StreamingWordCount

10. 编写 intro.html

11. 编写 streaming.html

12. 编写 UrlCount
