package com.alipay.common

import org.apache.spark.{SparkConf, SparkContext}

class SqlJson {

}

object SqlJson {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("jsonLoad")
    conf.setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val dfs = sqlContext.read.json("/person.json")
    dfs.show()
  }
}
