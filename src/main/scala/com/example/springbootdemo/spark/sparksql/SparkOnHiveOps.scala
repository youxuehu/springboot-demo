package com.example.springbootdemo.spark.sparksql

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

class SparkOnHiveOps {

}
/**
 *  关于Spark 和 Hive  的一个集成
 */
object SparkOnHiveOps extends App{
  val conf = new SparkConf().setAppName("SparkHiveOps").setMaster("local[2]")
  val sc  = new  SparkContext(conf)
  val hiveContext = new HiveContext(sc)
  /**
   * 查询hvie中的数据
   * */
  val df = hiveContext.table("test001");
  df.show()
  /**
   * 向hive中写数据
   * teacher_info
   *     name,height
   * teacher_basic
   *     name,age,married,children
   *  这是两张有关联的表，关联字段是name，
   *  需求：
   *    teacher_info和teacher_basic做join操作，求出每个teacher的name，age，height，married，children
   *    要求height>180
   */
  //在hive中创建相应的表
  hiveContext.sql("DROP TABLE IF EXISTS teacher_basic")
  hiveContext.sql("CREATE TABLE if not exists teacher_basic(" +
    "name string, " +
    "age int, " +
    "married boolean, " +
    "children int) " +
    "row format delimited " +
    "fields terminated by ','")
  //向teacher_basic表中加载数据
  hiveContext.sql("LOAD DATA LOCAL INPATH '/opt/data/spark/teacher_basic.txt' INTO TABLE teacher_basic")
  //创建第二张表
  hiveContext.sql("DROP TABLE IF EXISTS teacher_info")
  hiveContext.sql("CREATE TABLE teacher_info(name string, height int) row format delimited fields terminated by ','")
  hiveContext.sql("LOAD DATA LOCAL INPATH '/opt/data/spark/teacher_info.txt' INTO TABLE teacher_info")
  //执行多表关联
  val joinDF = hiveContext.sql("select b.name, b.age, b.married, b.children, i.height from teacher_basic b left join teacher_info i on b.name = i.name where i.height > 180")
  hiveContext.sql("DROP TABLE IF EXISTS teacher")
  joinDF.show()
  joinDF.write.saveAsTable("teacher")
  sc.stop()

}
