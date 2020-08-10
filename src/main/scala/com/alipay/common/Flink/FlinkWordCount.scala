package com.alipay.common.Flink
import org.apache.flink.api.scala._
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
object FlinkWordCount {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val text = env.readTextFile("hdfs://localhost:9000/Flink/Input/WordCount/The_Man_of_Property.txt")
    val counts = text.flatMap { _.toLowerCase.split("\\W+"). filter { _.nonEmpty } }
      .map { (_, 1) }
      .groupBy(0)
      .sum(1)
    val hadoopConf = new Configuration()
    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)
    val filePath = "hdfs://localhost:9000/Flink/Output/WordCount/"
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
