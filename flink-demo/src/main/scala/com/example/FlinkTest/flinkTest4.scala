package com.example.FlinkTest
import org.apache.flink.streaming.api.scala._
object flinkTest4 {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    // op 1: map
//    val stream = env.generateSequence(1, 10)
//    val streamMap = stream.map( x => x * 2)
//    streamMap.print()
    // op 2: flatmap
//    val stream = env.readTextFile("hdfs://master:9000/The_Man_of_Property.txt")
//    val streamFlatmap = stream.flatMap( x => x.split(" "))
//    streamFlatmap.print()
    // op 3: filter
//    val stream = env.generateSequence(1, 10)
//    val streamfilter = stream.filter( x => x != 6)
//    streamfilter.print()
    // op 4: KeyBy
    val stream = env.readTextFile("hdfs://localhost:9000/The_Man_of_Property.txt")
    val streamTmp = stream.flatMap( x => x.split(" ")).map( x => (x, 1))
    val streamKeyBy = streamTmp.keyBy(0)
//    streamKeyBy.print()
    val streamReduce = streamKeyBy.reduce(
      (x, y) => (x._1, x._2 + y._2)
    )
    streamReduce.print()
    env.execute("FlinkTestJob")
  }
}
