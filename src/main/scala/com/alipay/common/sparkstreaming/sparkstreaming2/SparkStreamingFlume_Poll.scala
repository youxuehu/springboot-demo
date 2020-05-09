package com.alipay.common.sparkstreaming.sparkstreaming2

import java.net.InetSocketAddress

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.flume.{FlumeUtils, SparkFlumeEvent}
import org.apache.spark.{SparkConf, SparkContext}

class SparkStreamingFlume_Poll {

}

/**
 * ./bin/spark-submit --master local[2] --class com.alipay.common.sparkstreaming.sparkstreaming2.SparkStreamingFlume_Poll  --jars jars/spark-streaming-flume_2.11-2.0.2.jar,jars/spark-streaming-flume-sink_2.11-2.0.2.jar  ~/springboot-demo-0.0.1-SNAPSHOT.jar
 */
object SparkStreamingFlume_Poll {
  def main(args: Array[String]): Unit = {
    //1、创建sparkConf
    val sparkConf: SparkConf = new SparkConf().setAppName("SparkStreamingFlume_Poll").setMaster("spark://leader:7077")
    //2、创建sparkContext
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("WARN")
    //3、创建StreamingContext
    val ssc = new StreamingContext(sc, Seconds(5))
    //定义一个flume地址集合，可以同时接受多个flume的数据
    val address = Seq(new InetSocketAddress("leader", 9999), new InetSocketAddress("worker1", 9999))
    //4、获取flume中数据
    val stream: ReceiverInputDStream[SparkFlumeEvent] = FlumeUtils.createPollingStream(ssc, address, StorageLevel.MEMORY_AND_DISK_SER_2)
    //5、从Dstream中获取flume中的数据  {"header":xxxxx   "body":xxxxxx}
    val lineDstream: DStream[String] = stream.map(x => new String(x.event.getBody.array()))
    //6、切分每一行,每个单词计为1
    val wordAndOne: DStream[(String, Int)] = lineDstream.flatMap(_.split(" ")).map((_, 1))
    //7、相同单词出现的次数累加
    val result: DStream[(String, Int)] = wordAndOne.reduceByKey(_ + _)
    //8、打印输出
    result.print()
    //开启计算
    ssc.start()
    ssc.awaitTermination()
  }
}
