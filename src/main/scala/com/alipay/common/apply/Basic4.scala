package com.alipay.common.apply

/**
 *  Created by youxuehu on 08-03-2020
 */

class ApplyTest{
  def apply() = "Apply"
  def test: Unit ={
    println("test")
  }
}

// 声明一个静态类
object ApplyTest{
  var count = 0
  def apply() = new ApplyTest

  def static: Unit ={
    println(" i am a statis method")
  }
  def inr = {
    count = count + 1
  }
}
class Basic4 {

}

object Basic4 extends App {
//  ApplyTest.static // 调用静态方法
//  val a = ApplyTest()
//  a.test


//  val t = new ApplyTest
//  println(t()) //调用方法
//  println(t) //打印类的地址
  //递增
  for (i <- 1 to 10) {
    ApplyTest.inr
    println(ApplyTest.count)
  }
  println(ApplyTest.count)
}