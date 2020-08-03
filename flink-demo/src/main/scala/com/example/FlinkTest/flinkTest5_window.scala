package com.example.FlinkTest

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

object flinkTest5_window {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.socketTextStream("localhost", 9988)
    val streamKeyBy = stream.map( x => (x, 1L)).keyBy(0)
    // method 1
    val streamWindow = streamKeyBy.countWindow(5).reduce(
      (x, y) => (x._1, x._2 + y._2)
    )
    // method 2
//    val streamWindow = streamKeyBy.countWindow(5, 2).reduce(
//      (x, y) => (x._1, x._2 + y._2)
//    )
    // method 3
//    val streamWindow = streamKeyBy.timeWindow(Time.seconds(5)).reduce(
//            (x, y) => (x._1, x._2 + y._2)
//          )
//    streamWindow.print()
    // method 4
//    val streamWindow = streamKeyBy.timeWindow(Time.seconds(10), Time.seconds(2)).reduce(
//      (x, y) => (x._1, x._2 + y._2)
//    )
    // method 5: watermark
    streamWindow.print()
    env.execute("window job")

  }
}
