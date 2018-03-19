package spark.data

import org.apache.spark.sql.SparkSession
;

/**
  * DataFrame API基本操作
  *
  * 1. 编写代码
  * hadoop fs -put people.json /
  *
  * 2. 运行代码
  */
object DataFrameApp {

  def main(args: Array[String]) {

    val spark = SparkSession.builder().appName("DataFrameApp").master("local[2]").getOrCreate()

    // val in = "file:///home/ricky/data/spark/sql/people.json"
    // val in = "/home/ricky/data/spark/sql/people.json"
    val in  = "hdfs://ricky:9000/people.json"

    // 将json文件加载成一个dataframe
    val peopleDF = spark.read.format("json").load(in)

    //peopleDF.toDF() ---> 入库

    // 输出dataframe对应的schema信息
    peopleDF.printSchema()
//    root
//    |-- age: long (nullable = true)
//    |-- name: string (nullable = true)

    // 输出数据集的前20条记录
    peopleDF.show()
//    +----+-------+
//    | age|   name|
//    +----+-------+
//    |  18|  ricky|
//      |  20|caopeng|
//      |null|   peng|
//      +----+-------+

    //查询某列所有的数据： select name from table
    peopleDF.select("name").show()
//    +-------+
//    |   name|
//    +-------+
//    |  ricky|
//    |caopeng|
//    |   peng|
//    +-------+

    // 查询某几列所有的数据，并对列进行计算： select name, age+10 as age2 from table
    peopleDF.select(peopleDF.col("name"), (peopleDF.col("age") + 10).as("age2")).show()
//    +-------+----+
//    |   name|age2|
//    +-------+----+
//    |  ricky|  28|
//      |caopeng|  30|
//      |   peng|null|
//      +-------+----+

    //根据某一列的值进行过滤： select * from table where age>19
    peopleDF.filter(peopleDF.col("age") > 19).show()
//    +---+-------+
//    |age|   name|
//    +---+-------+
//    | 20|caopeng|
//      +---+-------+

    //根据某一列进行分组，然后再进行聚合操作： select age,count(1) from table group by age
    peopleDF.groupBy("age").count().show()
//    +----+-----+
//    | age|count|
//    +----+-----+
//    |null|    1|
//      |  18|    1|
//      |  20|    1|
//      +----+-----+

    spark.stop()
  }

}
