package com.badou.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

object wordcountKafkaStreaming {

  def updateFunction(currentValues: Seq[Int], preValues: Option[Int]): Option[Int] = {
    val current = currentValues.sum
    val pre = preValues.getOrElse(0)
    Some(current + pre)
  }

  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("wordcountKafkaStreaming").set("spark.cores.max", "8")
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    ssc.checkpoint("hdfs://leader:9000/hdfs_checkpoint")

    val zkQuorum = "leader:2181,worker1:2181,worker2:2181"
    val groupId = "group_1"

    // val lines = ssc.socketTextStream(args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER)
    // val topicAndLine: ReceiverInputDStream[(String, String)] =
    //    KafkaUtils.createStream(ssc, zkQuorum, groupId, topic, StorageLevel.MEMORY_ONLY)
    // params: [ZK quorum], [consumer group id], [per-topic number of Kafka partitions to consume]
    val topicAndLine: ReceiverInputDStream[(String, String)] =
      KafkaUtils.createStream(ssc, zkQuorum, groupId,
        Map("kafka_streaming_topic_0510" -> 1), StorageLevel.MEMORY_AND_DISK_SER)

    val lines: DStream[String]  = topicAndLine.map{ x =>
      x._2
    }

    val words = lines.flatMap(_.split(" "))
    //val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    val wordCounts = words.map(x => (x, 1)).updateStateByKey(updateFunction _)
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
