package com.example.FlinkTest

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

object flinkTest2 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.socketTextStream("localhost", 9988)
    stream.print()
    env.execute("FlinkTestJob")
  }
}
