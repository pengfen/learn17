package spark.sql

import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}
;

/**
  *
cd $SPARK_HOME

  bin/spark-submit \
  --class com.spark_sql SpecifyingSchema \
  --master spark://ricky:7077 \
  /home/ricky/IdeaProjects/spark-learn/sparktrain-1.0.jar \
  hdfs://ricky:9200/person.txt \
  hdfs://ricky/out

  查看运行结果
  hdfs dfs -cat hdfs://ricky:9200/out/part*
  */
object SpecifyingSchema {

  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      // hdfs://ricky:9200/person.txt /home/ricky/data/out1
      System.err.println("Usage: <InferringSchema> in_path out_path")
      System.exit(1)
    }
    // 创建 SparkConf() 并设置App名称
    val conf = new SparkConf().setAppName("SpecifyingSchema").setMaster("local[2]")
    // SQLContext 要依赖 SparkContext
    val sc = new SparkContext(conf)
    // 创建SQLContext
    val sqlContext = new SQLContext(sc)

    // 从指定的地址创建RDD
    val personRDD = sc.textFile(args(0)).map(_.split(","))
    // 通过StructType 直接指定每个字段的schema
    val schema = StructType(
      List(
        StructField("id", IntegerType, true),
        StructField("name", StringType, true),
        StructField("age", IntegerType, true)
      )
    )

    // 将 RDD 映射到 rowRDD
    val rowRDD = personRDD.map(p => Row(p(0).toInt, p(1).trim, p(2).toInt))

    // 将schema信息应用到rowRDD上
    val personDF = sqlContext.createDataFrame(rowRDD, schema)

    // 注册表
    personDF.registerTempTable("t_person")

    // 执行SQL
    val df = sqlContext.sql("select * from t_person order by age desc limit 4")
//    df.show()
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  3|caopeng3| 30|
//      |  2|caopeng2| 20|
//      |  1|caopeng1| 18|
//      +---+--------+---+

    // 将结果以JSON的方式存储到指定位置
    df.write.json(args(1))
//    ricky@ricky:~/data/out2$ cat part*
//      {"id":3,"name":"caopeng3","age":30}
//    {"id":2,"name":"caopeng2","age":20}
//    {"id":1,"name":"caopeng1","age":18}

    // 停止 Spark Context
    sc.stop()
  }
}
