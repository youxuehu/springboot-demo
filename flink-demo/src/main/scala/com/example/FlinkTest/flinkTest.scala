package com.example.FlinkTest

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

object flinkTest {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val stream = env.readTextFile("hdfs://localhost:9000/The_Man_of_Property.txt")
    println("##########输出HDFS文件内容#########")
    println("#################################")
    stream.print()
    env.execute("FlinkTestJob")
  }
}
