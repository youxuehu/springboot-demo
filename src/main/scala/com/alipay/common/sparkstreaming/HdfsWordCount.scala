package com.alipay.common.sparkstreaming

import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Chen Chao
 */
object HdfsWordCount {
  def main(args: Array[String]) {
    if (args.length < 3) {
      System.err.println("Usage: HdfsWordCount <master> <directory> <seconds>")
      System.exit(1)
    }

    StreamingExamples.setStreamingLogLevels()

    //新建StreamingContext
    val ssc = new StreamingContext(args(0), "HdfsWordCount", Seconds(args(2).toInt),
      System.getenv("SPARK_HOME"), StreamingContext.jarOfClass(this.getClass))
    //创建FileInputDStream，并指向特定目录
    new StreamingContext("")
    val lines = ssc.textFileStream(args(1))
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }
}

