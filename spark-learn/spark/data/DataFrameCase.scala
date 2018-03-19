package spark.data

import org.apache.spark.sql.SparkSession
;

/**
  * DataFrame中的操作操作
  *
  * 1. 编写代码
  *
  * 2. 测试 ---> 运行代码
  *
  * 编写代码时可通过 spark-shell 进行测试
  * spark-shell --master local[2] --jar ~/software/mysql-connector-java-5.1.27-bin.jar
  */
object DataFrameCase {

  def main(args: Array[String]) {
    // spark 集群的入口 ---> spark2以前
    //    val conf = new SparkConf()
    //
    //    // A master URL must be set in your configuration 本地测试时如果没有配置master会出现此错误
    //    conf.setAppName("WordCount").setMaster("local[2]")
    //
    //    val sc = new SparkContext(conf)

    // spark2
    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()
    val sc = spark.sparkContext;

    // val in = "file:///home/ricky/data/spark/data/student.data"
    // val in = "/home/ricky/data/spark/data/student.data"
    // hadoop fs -put student.data /
    val in = "hdfs://ricky:9000/student.data"
    // RDD ==> DataFrame
    val rdd = sc.textFile(in)

    //注意：需要导入隐式转换
    import spark.implicits._
    val studentDF = rdd.map(_.split("\\|")).map(line => Student(line(0).toInt, line(1), line(2), line(3))).toDF()

    //show默认只显示前20条 长度超过显示...
    studentDF.show
    // 可通过传递参数改变显示多少条
    studentDF.show(30)
    // false 显示所有长度
    studentDF.show(30, false)

    // take 返回前十条
    studentDF.take(10)
    // first 返回第一条
    studentDF.first()
    // head 返回前几条
    studentDF.head(3)

    // 显示emain列
    studentDF.select("email").show(30,false)

    // 过滤 name为空的记录
    studentDF.filter("name=''").show
    // 过滤 name为空或NULL的记录
    studentDF.filter("name='' OR name='NULL'").show

    //name以M开头的人
    studentDF.filter("SUBSTR(name,0,1)='M'").show

    // 排序
    studentDF.sort(studentDF("name")).show
    studentDF.sort(studentDF("name").desc).show

    studentDF.sort("name","id").show
    studentDF.sort(studentDF("name").asc, studentDF("id").desc).show

    studentDF.select(studentDF("name").as("student_name")).show


    val studentDF2 = rdd.map(_.split("\\|")).map(line => Student(line(0).toInt, line(1), line(2), line(3))).toDF()

    studentDF.join(studentDF2, studentDF.col("id") === studentDF2.col("id")).show

    spark.stop()
  }

  case class Student(id: Int, name: String, phone: String, email: String)

}
