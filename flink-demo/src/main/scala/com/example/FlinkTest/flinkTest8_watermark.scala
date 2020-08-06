package com.example.FlinkTest

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.{SlidingEventTimeWindows, TumblingEventTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time

object flinkTest8_watermark {

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    import org.apache.flink.streaming.api.scala._

    val stream = env.socketTextStream("leader", 9988).assignTimestampsAndWatermarks(
      new BoundedOutOfOrdernessTimestampExtractor[String](Time.milliseconds(0)) {
        override def extractTimestamp(t: String): Long = {
          val eventTime = t.split(" ")(0).toLong
          print(eventTime)
          eventTime
        }
      }
    ).map(item => (item.split(" ")(1), 1L)).keyBy(0)

//    val streamWindow = stream.window(TumblingEventTimeWindows.of(Time.seconds(5)))
    // scope = 10s, every 5s trigger once
    val streamWindow = stream.window(
      SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)))

    val streamReduce = streamWindow.reduce(
      (x, y) => (x._1, x._2 + y._2)
    )

    streamReduce.print()

    env.execute("WM")

  }
}