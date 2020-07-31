package com.alipay.common.spark.sparkstreaming

class HDFSWordCount {

}
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 用SparkStreaming在HA模式下的HDFS跑WordCount程序
 */
object HDFSWordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("HDFSWordCount")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Seconds(10))
    /**
     * 数据的输入
     */
    //监控的是一个目录即文件夹，新增文件就可以接收到了
    val fileDStream: DStream[String] = ssc.textFileStream("hdfs://leader:9000/spark/streaming")
    /**
     * 数据的处理
     */
    val wordCountDStream: DStream[(String, Int)] = fileDStream.flatMap(_.split(","))
      .map((_, 1))
      .reduceByKey(_ + _)

    /**
     * 数据的输出
     */
    wordCountDStream.print()

    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
  }

}