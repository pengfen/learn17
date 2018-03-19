package spark.sql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import spark.sql.domain.Person

/**
  *
  cd $SPARK_HOME

  bin/spark-submit \
  --class com.spark_sql InferringSchema \
  --master spark://ricky:7077 \
  /home/ricky/IdeaProjects/spark-learn/sparktrain-1.0.jar \
  hdfs://ricky:9200/person.txt \
  hdfs://ricky/out

  查看运行结果
  hdfs dfs -cat hdfs://ricky:9200/out/part*
  */
// 通过反射推断
object InferringSchema {

  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      // hdfs://ricky:9200/person.txt /home/ricky/data/out1
      System.err.println("Usage: <InferringSchema> in_path out_path")
      System.exit(1)
    }
    // 创建SparkConf()并设置App名称
    val conf = new SparkConf().setAppName("InferringSchema").setMaster("local[2]")
    // sqlContext要依赖 SparkContext
    val sc = new SparkContext(conf)
    // 创建SQLContext
    val sqlContext = new SQLContext(sc)

    // 从指定的地址创建RDD
    val lineRDD = sc.textFile(args(0)).map(_.split(","))

    // 创建case class
    // 将RDD和case class 关联
    val personRDD = lineRDD.map(x => Person(x(0).toInt, x(1), x(2).toInt))

    // 导入隐式转换 如果不导入无法将RDD转换成DataFrame
    // 将RDD转换成DataFrame
    import sqlContext.implicits._
    val personDF = personRDD.toDF()
    // 注册表
    personDF.registerTempTable("t_person")
    // 传入SQL
    val df = sqlContext.sql("select * from t_person order by age desc limit 2")

//    df.show()
//    +---+--------+---+
//    | id|    name|age|
//    +---+--------+---+
//    |  3|caopeng3| 30|
//      |  2|caopeng2| 20|
//      +---+--------+---+
    // 将结果以JSON的方式存储到指定位置
    df.write.json(args(1))
//    ricky@ricky:~/data/out1$ cat part*
//      {"id":3,"name":"caopeng3","age":30}
//    {"id":2,"name":"caopeng2","age":20}

    // 停止spark context
    sc.stop()
  }
}

