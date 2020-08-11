package com.example.springbootdemo.spark.kafka

class SparkStreamingKafka {

}
import kafka.serializer.StringDecoder
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object SparkStreamingKafka {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("KafkaTest")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Seconds(2))
    //使用kafka是需要下面的目录的，因为SparkStreaming自己要维护一些东西的，要持久化，存到内存是易丢失的。
    ssc.checkpoint("hdfs://leader:9000/streamingkafka")
    /**
     * 数据的输入：KafkaUtils.createDirectStream
     *
     * def createDirectStream[K: ClassTag,V: ClassTag,KD <:Decoder[K]: ClassTag,VD <:Decoder[V]: ClassTag] (
     * 下面是三个参数：
     * ssc: StreamingContext,
     * kafkaParams: Map[String, String],
     * topics: Set[String])  可以一下子读多个topics，但是我们这里读一个topics就行了
     */
    //指定kafka broker的机器，也就是kafka的地址
    val kafkaParams = Map("metadata.broker.list" -> "leader:9092")
    val topics = Set("spark-stream-topic")
    //kafka读出来数据是kv的形式[String代表k的数据类型（k可就是偏移位置的信息, String代表v的数据类型（kafka内每一条数据）, StringDecoder代表的就是解码器, StringDecoder]
    //原来直接返回的是InputDStream[(String,String)]的KV数据类型，因为偏移位置的信息对我们是没有用的所以我们要.map(_._2)
    val kafkaDStream: DStream[String] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topics).map(_._2)

    /**
     * 数据的处理
     * 也已经比较正式了
     */
    kafkaDStream.flatMap(_.split(","))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
  }
}