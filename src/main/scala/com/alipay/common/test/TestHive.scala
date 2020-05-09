package com.alipay.common.test

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
object TestHive {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("TestHive").setMaster("local[2]")
    val spark = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()
    spark.sql("select * from test002").collect().foreach(println) }
}
