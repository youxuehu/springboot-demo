package com.example.FlinkTest

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

object flinkTest {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.readTextFile("hdfs://leader:9000/test.txt")
    val printWord = stream.print()
    println(printWord)


    env.execute("FlinkTestJob")
  }
}
