package com.alipay.common

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

class HiveSelect {

}
object HiveSelect {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    //  conf.setMaster("local");
    conf.setAppName("FirstSelect")
    val sc = new SparkContext(conf)
    val hiveContext = new HiveContext(sc)
    import hiveContext.implicits._
    import hiveContext.sql
    sql("show databases").collect().foreach(println)
    sc.stop()
  }

}
