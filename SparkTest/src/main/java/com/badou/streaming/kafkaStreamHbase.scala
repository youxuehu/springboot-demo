package com.badou.streaming

import org.apache.hadoop.hbase._
import org.apache.hadoop.hbase.client._
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

object HbaseHandler {
  def insert(row: String, column: String, value: String) {
    // Hbase配置
    val tableName = "sparkstream_kafkahbase_table" // 定义表名
    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.set("hbase.zookeeper.quorum", "leader,worker1,worker2")
    hbaseConf.set("hbase.zookeeper.property.clientPort", "2181")
    hbaseConf.set("hbase.defaults.for.version.skip", "true")
    val hTable = new HTable(hbaseConf, tableName)
    val thePut = new Put(row.getBytes)
    thePut.add("info".getBytes,column.getBytes,value.getBytes)
    hTable.setAutoFlush(false, false)
    // 写入数据缓存
    hTable.setWriteBufferSize(3*1024*1024)
    hTable.put(thePut)
    // 提交
    hTable.flushCommits()
  }
}

object kafkaStreamHbase {
  def main(args: Array[String]) {

    val zkQuorum = "leader:2181,worker1:2181,worker2:2181"
    val group = "group_1"
    val topics = "kafka_streaming_topic_0510"
    val numThreads = 1
    var output="hdfs://leader:9000/stream_out/spark-log.txt"

    val sparkConf = new SparkConf().setAppName("kafkaStreamHbase").setMaster("local[2]")
    val ssc =  new StreamingContext(sparkConf, Seconds(10))
    ssc.checkpoint("hdfs://leader:9000/hdfs_checkpoint")

    val topicpMap = topics.split(",").map((_,numThreads.toInt)).toMap
    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicpMap).map(_._2)
    // lines.print()
    // lines.saveAsTextFiles("hdfs://master:9000/stream_out/sparkstreaming_hbasetest.log")
    val line = lines.flatMap(_.split("\n"))
    val words = line.map(_.split("\\|"))
    words.foreachRDD(rdd => {
      rdd.foreachPartition(partitionOfRecords => {
        partitionOfRecords.foreach(pair => {
          val key = pair(0)
          val col = pair(1)
          val value = pair(2)
          println(key + "_" + col + " : " + value)
          HbaseHandler.insert(key, col, value)
        })
      })
    })
    ssc.start()
    ssc.awaitTermination()
  }
}