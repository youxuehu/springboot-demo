package com.badou.scalaBase

import org.apache.spark.{SparkConf, SparkContext}

object uWatchList {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("userwatchlist")
    val sc = new SparkContext(conf)
    val input_path = sc.textFile("/root/codes/spark_data/train_new.data")
    val out_path = "/root/codes/spark_data/uwatchlist"
    //filter low score
    input_path.filter{x=>
      val ss= x.split("\t")
      ss(2).toDouble>2.0
    }.map{x=>
      val ss = x.split("\t")
      val user_id = ss(0)
      val item_id = ss(1)
      val score = ss(2).toDouble
      (user_id,(item_id, score))
    }.groupByKey().map{x=>
      val user_id = x._1
      val item_score_list = x._2
      val tmp_arr = item_score_list.toArray.sortWith(_._2 > _._2)
      var watchlen = tmp_arr.length
      if(watchlen>5)(watchlen=5)

      val strbuf = new StringBuilder
      for(i <- 0 until watchlen){
        strbuf ++= tmp_arr(i)._1
        strbuf.append(":")
        strbuf ++= tmp_arr(i)._2.toString
        strbuf.append(" ")
      }
      user_id+"\t"+strbuf
    }.saveAsTextFile(out_path)

  }

}
