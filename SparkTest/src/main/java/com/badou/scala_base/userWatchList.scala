package com.badou.scala_base

import org.apache.spark.{SparkConf, SparkContext}

object userWatchList {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordcount")
    val sc = new SparkContext(conf)
    val input_path = sc.textFile("/train_new.data")
    val output_path = "/userwatchlist_result1"
    input_path.filter { x =>
      val ss = x.split("\t")
      ss(2).toDouble>2.0
    }.map { x =>
      val ss = x.split("\t")
      val user_id = ss(0).toString
      val item_id = ss(1).toString
      val score = ss(2).toString
      (user_id, (item_id, score))
    }.groupByKey().map{x =>
      val user_id = x._1
      val item_score_list = x._2
      val tmp_arr = item_score_list.toArray.sortWith(_._2>_._2)
      var watch_len = tmp_arr.length
      if (watch_len >= 5){
        watch_len = 5
      }
      val strbuf = new StringBuilder
      for (i <- 0 until watch_len){
        strbuf ++= tmp_arr(i)._1
        strbuf.append(":")
        strbuf ++= tmp_arr(i)._2
        strbuf.append(" ")
      }
      user_id +"\t" +strbuf

    }.saveAsTextFile(output_path)

  }

}
