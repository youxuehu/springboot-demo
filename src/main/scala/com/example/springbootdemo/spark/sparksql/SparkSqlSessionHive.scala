package com.example.springbootdemo.spark.sparksql

class SparkSqlSessionHive {

}

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

/**
 * 通过spark操作hive  把hive.site.xml放到resources中即可把元数据信息写入配置的mysql中
 */
object SparkSqlSessionHive {
  def main(args: Array[String]): Unit = {
    //创建sparkSession
    val sparkSession: SparkSession = SparkSession.builder().appName("HiveSupport").master("local[2]").enableHiveSupport().getOrCreate()

    //获取sc
    val sc: SparkContext = sparkSession.sparkContext
    sc.setLogLevel("WARN")

    //操作hive
    //    sparkSession.sql("create table if not exists person(id int,name string,age int) row format delimited fields terminated by ','")

    //    sparkSession.sql("load data local inpath './data/person.txt' into table person")
    sparkSession.sql("select * from test001").show()

    sparkSession.stop()

  }
}