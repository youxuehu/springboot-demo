package com.example.springbootdemo.spark.wordcount

import org.apache.hadoop.fs.Path
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author youxuehu
 * 统计 word count
 */
// --input "hdfs://localhost:9000/Spark/Input/WordCount/*/"
// --output "hdfs://localhost:9000/Spark/Output/WordCount"
// spark-submit --class org.apache.spark.examples.SparkPi --master local examples/jars/spark-examples_2.11-2.0.2.jar
// spark-submit --class org.apache.spark.examples.SparkPi --master spark://localhost:7077 examples/jars/spark-examples_2.11-2.0.2.jar
// spark-submit --class org.apache.spark.examples.SparkPi --master yarn-cluster examples/jars/spark-examples_2.11-2.0.2.jar
// spark-submit --class org.apache.spark.examples.SparkPi --master yarn-client examples/jars/spark-examples_2.11-2.0.2.jar
object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
//    conf.setMaster("local")
    conf.setAppName("wordcount")
    val sc = new SparkContext(conf)
    var input = "hdfs://localhost:9000/Spark/Input/WordCount/*"
    var output = "hdfs://localhost:9000/Spark/Output/WordCount"
    // 接收输入参数
    if (args.length == 1) {
      input = args(0)
    }
    if (args.length == 2) {
      input = args(0)
      output = args(1)
    }
    val rdd = sc.textFile(input)
    val newRdd = rdd.repartition(1)
    // 计算文章中单词出现的个数，并按照个数倒叙排序, 截取前10条数据
    val data = newRdd.flatMap(_.split(" "))
      .map((_,1)) // map  输出
      .reduceByKey(_+_) // reduce计算
      .sortBy(_._2,false) // sort排序
      .repartition(1)
      .map{x =>
            x._1 + "\t" + x._2
      }
    val hadoopConf = sc.hadoopConfiguration
    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)
    val path = new Path(output);
    if(hdfs.exists(path)){
      // 如果输出目录存在，则删除
      hdfs.delete(path)
    }
    // data.foreach(println)
    // 将list转换成rdd
    val new_data = sc.parallelize(data.collect())
    // 最终结果写入HDFS
    new_data.saveAsTextFile(output)
  }
}
