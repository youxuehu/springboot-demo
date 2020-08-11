package com.example.springbootdemo.spark.mysql

class OutputTest {

}
/**
 * Create by jenrey on 2018/5/13 20:27
 */
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.ReceiverInputDStream

/**
 * 接收nc的数据，并把数据存到mysql表中
 */
object OutputTest {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("OutputTest")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Seconds(2))
    ssc.checkpoint("hdfs://leader:9000/StreamingCheckPoint3")
    /**
     * 数据的输入
     */
    val dstream: ReceiverInputDStream[String] = ssc.socketTextStream("leader",9999)


    val wordCountDStream = dstream.flatMap(_.split(","))
      .map((_, 1))
      .updateStateByKey((values: Seq[Int], state: Option[Int]) => {
        val currentCount = values.sum
        val lastCount = state.getOrElse(0)
        Some(currentCount + lastCount)
      })

    /**
     * 数据的输出
     */
    wordCountDStream.foreachRDD( rdd=>{
      rdd.foreachPartition( paritition =>{
        //从连接池中获取连接
        val connection = ConnectionPool.getConnection()
        //获取Statement对象（用来发送sql指令）
        val statement = connection.createStatement()
        paritition.foreach{
          case (word,count) =>{
            val sql=s"insert into hive.wordcount values(now(),'$word',$count)"
            print(sql)
            //借助于Statement发送sql指令
            statement.execute(sql)
          }
        }
        //把connection对象再还回给连接池
        ConnectionPool.returnConnection(connection)
      } )
    })

    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
  }

}
