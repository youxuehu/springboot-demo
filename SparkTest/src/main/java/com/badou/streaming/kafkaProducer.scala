package com.badou.streaming

import java.util.HashMap

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.mutable.ArrayBuffer

object kafkaProducer {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("KafkaProducer")
    val sc = new SparkContext(conf)
    val array = ArrayBuffer("how","do","you")
    ProducerSender(array)
  }

  def ProducerSender(args: ArrayBuffer[String]): Unit = {
    if (args != null) {
      // val brokers = "192.168.226.10:9092,192.168.226.11:9092,192.168.226.12:9092"
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
