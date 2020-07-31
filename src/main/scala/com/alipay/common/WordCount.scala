package com.alipay.common

import org.apache.hadoop.fs.Path
import org.apache.spark.{SparkConf, SparkContext}

class  WordCount{

}

/**
 * 统计 word count
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[2]")
    conf.setAppName("wordcount")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("hdfs://leader:9000/wordcount.txt")
    val newRdd = rdd.repartition(1)
    val data = newRdd.flatMap(_.split(" "))
      .map((_,1)).reduceByKey(_+_).map{x =>
      x._1 + "\t" + x._2
    }
    /**
     * hadoop
     */
    val hadoopConf = sc.hadoopConfiguration
    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)
    val filePath = "hdfs://leader:9000/wordcount_output2"
    val path = new Path(filePath);
    if(hdfs.exists(path)){
      /**
       * 如果输出目录存在，则删除
       */
      hdfs.delete(path)
    }
    data.saveAsTextFile("hdfs://leader:9000/wordcount_output2")
    data.foreach(println)
  }
}
