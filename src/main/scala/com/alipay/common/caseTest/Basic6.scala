package com.alipay.common.caseTest

/**
 * case class 一般用于模式匹配上
 */
class Basic6 {

}
case class Book(name: String, author: String){

}
object Basic6 extends App {
  val book = Book("spring", "tiger")

  book match {
    // 判断是不是Book类型的值
    case Book(name, author) => println("this. is a book")
    case _ => println("unknow")
  }
}
