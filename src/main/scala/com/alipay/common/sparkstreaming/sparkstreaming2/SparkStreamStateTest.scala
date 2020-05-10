package com.alipay.common.sparkstreaming.sparkstreaming2

import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * fileName : Test11StreamingWordCount
 * Created by 970655147 on 2016-02-12 13:21.
 */
object SparkStreamStateTest {


  // 基于sparkStreaming的wordCount
  // 环境windows7 + spark1.2 + jdk1.7 + scala2.10.4
  // 1\. 启动netcat [nc -l -p 9999]
  // 2\. 启动当前程序
  // 3\. netcat命令行中输入数据
  // 4\. 回到console, 查看结果[10s 之内]
  // *******************************************
  // 每一个print() 打印一次
  // -------------------------------------------
  // Time: 1455278620000 ms
  // -------------------------------------------
  // Another Infomation !
  // *******************************************
  // inputText : sdf sdf lkj lkj lkj lkj
  // MappedRDD[23] at count at Test11StreamingWordCount.scala:39
  // 2
  // (sdf,2), (lkj,4)
  def main(args :Array[String]) = {

    // Create a StreamingContext with a local master
    // Spark Streaming needs at least two working thread
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val sc = new StreamingContext("local[2]", "NetworkWordCount", Seconds(10) )
    sc.sparkContext.setLogLevel("WARN")
    sc.checkpoint("hdfs://leader:9000/spark-stream/checkpoint/");
    // Create a DStream that will connect to serverIP:serverPort, like localhost:9999
    val lines = sc.socketTextStream("leader", 9999)
    // Split each line into words
    // 以空格把收到的每一行数据分割成单词
    val words = lines.flatMap(_.split(" "))
    // 在本批次内计单词的数目
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _).updateStateByKey(updateFunc)
    // 打印每个RDD中的前10个元素到控制台
    wordCounts.print()
    wordCounts.repartition(1).saveAsTextFiles("hdfs://leader:9000/spark-stream/data/")
    sc.start()
    sc.awaitTermination()
  }
  val updateFunc = (values: Seq[Int], state: Option[Int]) => {
    val currentCount = values.sum
    val previousCount = state.getOrElse(0)
    Some(currentCount + previousCount)
  }

}
