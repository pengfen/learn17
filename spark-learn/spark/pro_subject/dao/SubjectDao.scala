package spark.pro_subject.dao

import java.sql.{Connection, PreparedStatement}

import spark.pro_subject.domain.Subject
import spark.utils.MySQLUtils

import scala.collection.mutable.ListBuffer

/**
  * 添中数据
  */
object SubjectDao {

  /**
    * 批量保存subjectTopN数据
    */
  def insertSubjectTonN(list: ListBuffer[Subject]): Unit = {

    var connection: Connection = null
    var pstmt: PreparedStatement = null

    try {
      connection = MySQLUtils.getConnection()

      connection.setAutoCommit(false) //设置手动提交

      // (mobile, bs, time, x, y)
      val sql = "insert into subject(subject, url, click_count) values (?,?,?) "
      pstmt = connection.prepareStatement(sql)

      for (ele <- list) {
        pstmt.setString(1, ele.subject)
        pstmt.setString(2, ele.url)
        pstmt.setInt(3, ele.click_count)

        pstmt.addBatch()
      }

      pstmt.executeBatch() // 执行批量处理
      connection.commit() //手工提交
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtils.release(connection, pstmt)
    }
  }
}
