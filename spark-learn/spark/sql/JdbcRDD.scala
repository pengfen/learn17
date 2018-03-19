package spark.sql

import java.util.Properties

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
;

// 4. 查看结果
//mysql> select * from person;
//+----+-------+-----+
//| id | name  | age |
//+----+-------+-----+
//|  1 | tom   |   5 |
//|  2 | jerry |   3 |
//|  3 | kitty |   6 |
//+----+-------+-----+
object JdbcRDD {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("JdbcRDD").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // 通过并行化创建RDD
    val personRDD = sc.parallelize(Array("1 tom 5", "2 jerry 3", "3 kitty 6")).map(_.split(" "))

    // 通过 StructType 直接指定每个字段的schema
    val schema = StructType(
      List(
        StructField("id", IntegerType, true),
        StructField("name", StringType, true),
        StructField("age", IntegerType, true)
      )
    )

    // 将 RDD 映射到 rowRDD
    val rowRDD = personRDD.map(p => Row(p(0).toInt, p(1).trim, p(2).toInt))
    // 将schema 信息应用到rowRDD上
    val personDF = sqlContext.createDataFrame(rowRDD, schema)
    // 创建 Properties 存储数据库相关属性
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")
    // 将数据追加到数据库
    personDF.write.mode("append").jdbc("jdbc:mysql://localhost:3306/spark_learn", "spark_learn.person", prop)
    // 停止SparkContext
    sc.stop()
  }
}
