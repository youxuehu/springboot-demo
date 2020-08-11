package com.example.springbootdemo.sparkstreaming.sparkstreaming2

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

class NetworkWordCount {

}

/**
  执行命令：
  ./bin/spark-submit --master local[2]
  --class com.alipay.common.sparkstreaming.sparkstreaming2.NetworkWordCount
  ~/springboot-demo-0.0.1-SNAPSHOT.jar
  localhost 9999
 */
object NetworkWordCount {
  def main(args: Array[String]): Unit = {
    /**
     * 设置日志级别
     */
    import org.apache.log4j.{Level,Logger}
    Logger.getLogger("org.apache.spark").setLevel(Level.INFO)
    Logger.getLogger("org.apache.spark.sql").setLevel(Level.INFO)
    Logger.getLogger("org.apache.spark.streaming").setLevel(Level.INFO)

    val sparkConf = new SparkConf().setAppName("NetworkWordCount").setMaster("local")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    val lines = ssc.socketTextStream(args(0),args(1).toInt,StorageLevel.MEMORY_AND_DISK_SER)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x,1)).reduceByKey(_+_)
    wordCounts.print()
    println(wordCounts)
    wordCounts.saveAsTextFiles("/NetworkWordCount/NetworkWordCount")
    ssc.start()
    ssc.awaitTermination()
  }
}