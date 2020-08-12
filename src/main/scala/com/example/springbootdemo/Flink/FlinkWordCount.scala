package com.example.springbootdemo.Flink

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala._
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
object FlinkWordCount {
  def main(args: Array[String]): Unit = {
    val params: ParameterTool = ParameterTool.fromArgs(args)
    val env = ExecutionEnvironment.getExecutionEnvironment
    env.getConfig.setGlobalJobParameters(params)
    if (!params.has("input") || !params.has("output")) {
      println("Please use --input --output param")
      return;
    }
    env.setParallelism(1)
    val text = env.readTextFile(params.get("input"))
    val counts = text.flatMap { _.toLowerCase.split("\\W+"). filter { _.nonEmpty } }
      .map { (_, 1) }
      .groupBy(0)
      .sum(1)
      .map{x =>
        x._1 + "\t" + x._2
      }
    val hadoopConf = new Configuration()
    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)
    val filePath = params.get("output")
    val path = new Path(filePath);
    if(hdfs.exists(path)){
      // 如果输出目录存在，则删除
      hdfs.delete(path)
    }
    // counts.print()
    counts.writeAsText(filePath)
    env.execute("Text WordCount")
  }
}
