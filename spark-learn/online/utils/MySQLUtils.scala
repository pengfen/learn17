package online.utils

import java.sql.{Connection, PreparedStatement, DriverManager}

/**
  * MySQL操作工具类
  */
object MySQLUtils {

  /**
    * 获取数据库连接 (java方式)
    */
  def getConnection() = {
    DriverManager.getConnection("jdbc:mysql://localhost:3306/resource?user=root&password=123456")
  }

  /**
    * 释放数据库连接等资源
    * @param connection
    * @param pstmt
    */
  def release(connection: Connection, pstmt: PreparedStatement): Unit = {
    try {
      if (pstmt != null) {
        pstmt.close()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (connection != null) {
        connection.close()
      }
    }
  }

  def main(args: Array[String]) {
    println(getConnection())
  }

}
