package com.alipay.common.spark

import org.apache.spark.{SparkConf, SparkContext}

class SparkRddBasic {

}

// spark rdd 计算文本文件单词出现次数
object SparkRddBasic extends App {
  println("Sprak Rdd Start！！！！！")
  val result = new SparkContext(new SparkConf().setAppName("wordcount"))
    .textFile("/wordcount.txt").flatMap(_.split(" "))
    .map((_,1)).reduceByKey(_+_).map(x=>(x._2,x._1)).sortByKey(false)
    .map(x=>(x._2+"\t"+x._1)).repartition(1).collect()
  println("Spark Rdd Out Result!!!!!")
  println(result)
}
