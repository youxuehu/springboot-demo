package com.example.springbootdemo.spark.sparkstreaming

class DriverHAWordCount {

}
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 *
 */
object DriverHAWordCount {
  def main(args: Array[String]): Unit = {
    //注意本程序需要执行一次输入点数据，然后关闭再次执行就可以接着上次进行累加了
    val checkpointDirectory: String = "hdfs://leader:9000/StreamingCheckPoint3";

    def functionToCreateContext(): StreamingContext = {
      val conf = new SparkConf().setMaster("local[2]").setAppName("DriverHAWordCount")
      val sc = new SparkContext(conf)
      val ssc = new StreamingContext(sc, Seconds(2))
      ssc.checkpoint(checkpointDirectory)
      val dstream: ReceiverInputDStream[String] = ssc.socketTextStream("leader", 9999)
      val wordCountDStream = dstream.flatMap(_.split(","))
        .map((_, 1))
        .updateStateByKey((values: Seq[Int], state: Option[Int]) => {
          val currentCount = values.sum
          val lastCount = state.getOrElse(0)
          Some(currentCount + lastCount)
        })

      wordCountDStream.print()

      ssc.start()
      ssc.awaitTermination()
      ssc.stop()
      //最后一行代码就是返回的
      ssc
    }

    //从里面获取一个程序入口，如果checkpointDirectory目录里面有程序入口就用这个，如果没有就新new一个程序入口（或者说一个Driver服务）
    val ssc = StreamingContext.getOrCreate(checkpointDirectory, functionToCreateContext _)

    ssc.start()
    ssc.awaitTermination()
    ssc.stop()

  }

}