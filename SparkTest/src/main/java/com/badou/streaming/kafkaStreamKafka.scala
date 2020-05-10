package com.badou.streaming

import java.util.HashMap

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.collection.mutable.ArrayBuffer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

object kafkaStreamKafka {

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

    val array = ArrayBuffer[String]()

    lines.foreachRDD(rdd => {

      val count = rdd.count().toInt

      rdd.take(count).foreach(x => {
        array += x + "--read"
      })

      ProducerSender(array)

      array.clear()

    })

    ssc.start()
    ssc.awaitTermination()
  }

  def ProducerSender(args: ArrayBuffer[String]): Unit = {
    if (args != null) {
      // val brokers = "192.168.87.10:9092,192.168.87.11:9092,192.168.87.12:9092"
      val brokers = "leader:9092"
      // Zookeeper connection properties
      val props = new HashMap[String, Object]()
      props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
      props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer")
      props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer")
      val producer = new KafkaProducer[String, String](props)
      val topic = "kafka_streaming_topic_0510"
      // Send some messages
      for (arg <- args) {
        println("i have send message: " + arg)
        val message = new ProducerRecord[String, String](topic, null, arg)
        producer.send(message)
      }
      Thread.sleep(500)
      producer.close()
    }
  }
}
