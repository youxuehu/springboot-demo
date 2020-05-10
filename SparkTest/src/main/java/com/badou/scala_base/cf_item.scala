package com.badou.scala_base

import breeze.numerics.sqrt
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object cf_item {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]")setAppName("CF spark")
    val sc = new SparkContext(conf)
    //val lines = sc.textFile(args(0))
    //val output = args(1).toString
    val lines = sc.textFile("/root/codes/spark_data/train_new.data")
    val max_pres_per_user = 20
    val top_n = 5

    //Step 1. normalization, Obtain UI Matrix
    val UIMatrix = lines.map{x =>
      val ss = x.split("\t")
      val user_id = ss(0).toString
      val item_id = ss(1).toString
      val score = ss(2).toDouble
      (item_id, (user_id, score))
    }.groupByKey().flatMap{x =>
      val item_id = x._1
      val userScores = x._2
      var s = 0
      val us_list = userScores.toArray
      var sumScore = 0.0
      var u_is_arr = new ArrayBuffer[(String, (String, Double))]
      for (i <- 0 until us_list.length){
        //val user_id = us_list(i)._1
        val score = us_list(i)._2.toDouble
        sumScore += score*score
      }
      sumScore = sqrt(sumScore)
      for (i <- 0 until us_list.length) {
        val user_id = us_list(i)._1
        //val score = us_list(i)._2.toDouble
        u_is_arr += ((user_id,(item_id, us_list(i)._2.toDouble / sumScore)))
      }
      u_is_arr
      }.groupByKey()
    val iipair = UIMatrix.flatMap{x =>
      val is_arr = x._2.toArray
      var ii_s_arr = new ArrayBuffer[((String, String), Double)]()
      val user_id = x._1
      for (i <- 0 until is_arr.length -1){
        for (j <- i+1 until is_arr.length){
          val item_i = is_arr(i)._1
          val item_j = is_arr(j)._1
          val s = is_arr(i)._2 * is_arr(j)._2
          ii_s_arr +=(((item_i,item_j),s))
          ii_s_arr +=(((item_j,item_i),s))
        }
      }
      ii_s_arr
    }.groupByKey()
      //.saveAsTextFile("/root/codes/spark_data/cf_result_1")
    iipair.map{ x =>
      val itemij = x._1
      val ijscores = x._2
      val ijscores_array = ijscores.toArray
      var ijs = 0.0
      for (i<- 0 until ijscores_array.length){
        ijs += ijscores_array(i).toDouble
      }
      //itemij._1 + "\t" + itemij._2 + "\t" + ijs.toString
      (itemij._1,(itemij._2, ijs))
    }.groupByKey().map{x =>
      val itemi = x._1
      val rec_list = x._2
      val rec_arr = rec_list.toArray.sortWith(_._2 > _._2)

      var len = rec_arr.length
      if (len > top_n) {
        len = top_n
      }

      val s = new StringBuilder
      for (i <- 0 until len){
        val itemj = rec_arr(i)._1
        val ijs = rec_arr(i)._2
        s.append(itemj + ":" +ijs)
        if(i != len-1) {
          s.append(",")
        }
      }
       itemi + "\t" + s

    }.saveAsTextFile("/root/codes/spark_data/cf_result_1")



    }

    //Step 2. pairs

    // Step3. sum and output

}
