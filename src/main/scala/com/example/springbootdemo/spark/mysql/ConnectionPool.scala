package com.example.springbootdemo.spark.mysql

class ConnectionPool {

}
import java.sql.{Connection, DriverManager}

object ConnectionPool {
  private val max=8 ;//连接池的连接总数
  private val connectionNum=10;//每次产生的连接数
  private var conNum=0;//当前连接池已经产生的连接数

  import java.util
  private val pool=new util.LinkedList[Connection]();//连接池

  {
    Class.forName("com.mysql.jdbc.Driver")
  }
  /**
   * 释放连接
   */
  def returnConnection(conn:Connection):Unit={
    pool.push(conn);
  }
  /**
   * 获取连接
   */
  def getConnection():Connection={
    //同步代码块
    AnyRef.synchronized({
      if(pool.isEmpty()){
        for( i <- 1 to connectionNum){
          val conn=DriverManager.getConnection("jdbc:mysql://leader:3306/hive?useSSL=false","root","zhf123..");
          pool.push(conn);
          conNum+1;
        }
      }
      pool.poll();
    })

  }
}