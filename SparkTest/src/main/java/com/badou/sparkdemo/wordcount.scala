package com.badou.sparkdemo

import org.apache.spark.{SparkConf, SparkContext}

object wordcount {
  def main(args:Array[String]):Unit = {
    println("123")
    val conf = new SparkConf()
    conf.setMaster("local[2]")
    conf.setAppName("wordcount")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("/root/codes/mapreduce_wordcount_python")
    val data = rdd.flatMap(_.split(" "))
      .map((_,1)).reduceByKey(_+_).map{x =>
      x._1 + "\t" + x._2
    }
    data.saveAsTextFile("/root/codes/spark_data/wordcount_output3")
  }


}
