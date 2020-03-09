package com.alipay.common.matchTest

class Basic5 {

}

/**
 * match 语法
 * 相当于 Java 的  switch
 */
object Basic5 extends App{
  val value = 3;

  val result = value match {
    case 1 => "one"
    case 2 => "two"
    case _ => "some other number"
  }

  val result2 = value match {
    case i if i == 1 => "one"
    case i if i == 2 => "two"
    case _ => "some other number"
  }

  println("result of match is :" + result)
  println("result2 of match is :" + result2)
}