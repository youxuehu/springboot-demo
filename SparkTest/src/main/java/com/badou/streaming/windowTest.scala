package com.badou.streaming

import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel

object windowTest {
  def main(args: Array[String]) {
    if (args.length < 2) {
      System.err.println("Usage: wordCount <hostname> <port>")
      System.exit(1)
    }

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("windowTest")
    val ssc = new StreamingContext(sparkConf, Seconds(10))
    ssc.checkpoint("hdfs://leader:9000/hdfs_checkpoint")

    val lines = ssc.socketTextStream(args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKeyAndWindow((v1: Int, v2:Int) => v1 + v2, Seconds(30), Seconds(10))
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
