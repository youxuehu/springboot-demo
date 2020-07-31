package com.alipay.common
import org.apache.spark.sql.{SparkSession}
class SparkSqlTest {
}
object SparkSqlTest {
  def main(args: Array[String]): Unit = {
    /**
     * 创建spark session
     */
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    /**
     * 读取文件内容
     */
    val df = spark.read.json("/test.txt")
    df.show()
  }
}