package com.example.FlinkTest

import org.apache.flink.streaming.api.scala._

object flinkTest3 {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // method 1: from list
//    val stream = env.fromCollection(List(1,2,3,4))

    // method 2: from iterator
//    val iterator = Iterator(1,2,3,4)
//    val stream = env.fromCollection(iterator)

    // method 3: seq
    val stream = env.generateSequence(1, 10)
    stream.print()
    env.execute("FlinkTestJob")
  }
}
