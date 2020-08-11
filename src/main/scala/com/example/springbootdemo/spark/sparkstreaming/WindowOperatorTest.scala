package com.example.springbootdemo.spark.sparkstreaming

class WindowOperatorTest {

}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 需求：每隔4秒统计最近6秒的单词计数的情况
 * reduceByKeyAndWindow
 */
object WindowOperatorTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("WordBlack")
    val sc: SparkContext = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(2))
    /**
     * 数据的输入
     * 到目前为止这个地方还没有跟生产进行对接。
     */
    val dstream: ReceiverInputDStream[String] = ssc.socketTextStream("leader", 9999)

    /**
     * 数据的处理
     */
    val resultWordCountDStream: DStream[(String, Int)] = dstream.flatMap(_.split(","))
      .map((_, 1))

      /**
       * reduceFunc: (V, V) => V,  匿名函数-达到对单词次数进行累加的效果
       * windowDuration: Duration, 统计多少秒以内的数据-窗口的大小
       * slideDuration: Duration,  每隔多少时间-滑动的大小
       * //numPartitions: Int  指定分区数，要么跟核数有关要么和指定分区数有关
       * 注意：这两个数一定要是Seconds(2)的倍数
       */
      .reduceByKeyAndWindow((x: Int, y: Int) => x + y, Seconds(6), Seconds(4))


    /**
     * 数据的输出
     */
    resultWordCountDStream.print()
    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
  }
}