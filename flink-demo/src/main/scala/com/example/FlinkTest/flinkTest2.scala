package com.example.FlinkTest

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

object flinkTest2 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
//    val stream = env.readTextFile("hdfs://master:9000/u1.test")
    val stream = env.socketTextStream("leader", 9988)

    stream.print()

    env.execute("FlinkTestJob")
  }
}
