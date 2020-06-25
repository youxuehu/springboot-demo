package com.alipay.common

import org.apache.spark.{SparkConf, SparkContext}

class  WordCount{

}

/**
 * 统计 word count
 */
object WordCount {
  def main(args: Array[String]): Unit = {
//    println("123")
    val conf = new SparkConf()
//    conf.setMaster("local[2]")
    conf.setAppName("wordcount")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("hdfs://master:9000/wordcount.txt")
    val newRdd = rdd.repartition(1)
    val data = newRdd.flatMap(_.split(" "))
      .map((_,1)).reduceByKey(_+_).map{x =>
      x._1 + "\t" + x._2
    }
    data.saveAsTextFile("hdfs://master:9000/wordcount_output2")
  }

}
