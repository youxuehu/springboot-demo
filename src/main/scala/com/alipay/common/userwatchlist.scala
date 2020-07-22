package com.alipay.common

class userwatchlist {

}
import org.apache.spark.{SparkConf, SparkContext}

object userwatchlist {
  def main(args:Array[String]):Unit = {
    println("123")
    val conf = new SparkConf()
    //conf.setMaster("local[2]")
    conf.setAppName("userwatchlist")
    val sc = new SparkContext(conf)
    val input_path = sc.textFile("hdfs://master72:8020/train_new.data")
    val output_path = "hdfs://master72:8020/userwatchlist_result1"
    input_path.filter{x =>
      val ss = x.split("\t")
      ss(2).toDouble > 2.0
    }.map{x =>
      val ss = x.split("\t")
      val userid = ss(0).toString
      val itemid = ss(1).toString
      val score = ss(2).toString
      (userid,(itemid,score))
    }.groupByKey().map{x =>
      val userid = x._1
      val item_score_tuple_list = x._2
      val tmp_arr = item_score_tuple_list.toArray.sortWith(_._2 > _._2)
      var watchlen = tmp_arr.length
      if (watchlen > 5) {watchlen = 5}
      //top 5
      val strbuf = new StringBuilder
      for (i<-0 until watchlen){
        strbuf ++= tmp_arr(i)._1
        strbuf.append(":")
        strbuf ++= tmp_arr(i)._2
        strbuf.append(" ")
      }
      userid + "\t" + strbuf
    }.saveAsTextFile(output_path)
  }
}
