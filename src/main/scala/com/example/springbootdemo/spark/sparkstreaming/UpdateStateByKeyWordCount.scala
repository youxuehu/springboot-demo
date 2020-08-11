package com.example.springbootdemo.spark.sparkstreaming

class UpdateStateByKeyWordCount {

}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Create by jenrey on 2018/5/13 15:09
 */
object UpdateStateByKeyWordCount {
  def main(args: Array[String]): Unit = {
    /**
     * 初始化程序入口
     */
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("UpdateStateByKeyWordCount")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(2))
    //注意一定要设置checkpoint目录，否则程序报错,但是这个HDFS目录一定要有权限，这个目录不用提前创建，自动创建
    ssc.checkpoint("hdfs://leader:9000/StreamingCheckPoint")
    /**
     * 数据的输入
     */
    val dstream: ReceiverInputDStream[String] = ssc.socketTextStream("leader", 9999)
    /**
     * 数据的处理
     */
    val wordCountDStream: DStream[(String, Int)] = dstream.flatMap(_.split(","))
      .map((_, 1))
      //updateStateByKey(updateFunc: (Seq[V], Option[S]) => Option[S]) 注意里面是一个函数
      //Option：Some：有值，None：没值
      //ByKey:操作就是分组
      //you,1
      //you,1     => you,{1,1}和jump,{1}
      //jump,1
      //下面这个函数每一个key都会调用一次这个函数
      //所以values:Seq[Int]代表List{1,1}  state:Option[Int]代表上一次这个单词出现了多少次，如果上一次没出现过就是None，如果出现过就是Some该1次就1次该2次就2次
      .updateStateByKey((values: Seq[Int], state: Option[Int]) => {
        val current: Int = values.sum
        //上一次出现多少次如果有就有，没有就是0
        val lastCount: Int = state.getOrElse(0)
        //既然这个单词能调用这个方法，那么这个单词必然最少出现了一次就是Some，所以当前+上一次的就是这个单词一共出现多少次
        Some(current + lastCount)
      })

    /**
     * 数据的输出
     */
    wordCountDStream.print()

    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
  }
}