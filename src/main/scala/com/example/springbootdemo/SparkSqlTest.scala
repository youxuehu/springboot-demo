package com.example.springbootdemo

import org.apache.spark.sql.{SparkSession}
class SparkSqlTest {
}
object SparkSqlTest {

  // $example on:create_ds$
  case class Person(name: String, age: Long)
  // $example off:create_ds$


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
    val df = spark.read.json("/Users/youxuehu/IdeaProjects/springboot-demo/src/main/resources/resources/people.json")
    df.show()
  }
}