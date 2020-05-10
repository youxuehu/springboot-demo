package com.badou.scalaBase

import org.apache.spark.{SparkConf, SparkContext}

object wordCnt {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("wordcount")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("/root/codes/mapreduce_wordcount_python/The_Man_of_Property.txt")
    val out_rdd = rdd.flatMap{ x=>
      x.split(" ")
    }.map{x=>
      (x,1)
    }.groupByKey().map{x=>
      (x._1,x._2.sum)
    }

    //  reduceByKey(_+_).map{x=>
    //  x._1+'\t'+x._2.toString
    //}
    //  .saveAsTextFile("/root/codes/spark_data/wc_output")
  }
}
