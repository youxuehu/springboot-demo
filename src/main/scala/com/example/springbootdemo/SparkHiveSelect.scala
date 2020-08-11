package com.example.springbootdemo

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}
class SparkHiveSelect {
}
object SparkHiveSelect {
  def main(args: Array[String]): Unit = {
    /**
     * spark 查询 hive QL
     */
    val conf = new SparkConf()
    conf.setMaster("local");
    conf.setAppName("FirstSelect")
    val sc = new SparkContext(conf)
    val hiveContext = new HiveContext(sc)
    import hiveContext.sql
    /**
     * 查询hive 所有的database
     */
    println("########show database#######")
    sql("show databases").collect().foreach(println)
    sc.stop()
  }

}
