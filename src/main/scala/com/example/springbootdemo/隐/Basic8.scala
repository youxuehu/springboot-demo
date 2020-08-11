package com.example.springbootdemo.隐

class Basic8 {

}

class A {

}

class RichA(a : A) {
  def rich: Unit ={
    println("rich ...")
  }
}

/**
 * 隐式类型 参数
 */
object Basic8 extends App {

  implicit def a2RichA(a : A) = new RichA(a)
  val a = new A
  // 输出 rich ...
  a.rich

  /**
   * 隐式参数
   */
  def testParam(implicit name : String): Unit ={
    println(name)
  }

  implicit val name = "implicit !!!"
  // 输出 implicit !!!
  testParam
  // 输出 xxx
  testParam("xxx")

  // 隐式类
  implicit class Calculator(x : Int) {
    def add(a : Int) : Int = a +1
  }
  // 输出  2
  println(1.add(1))
}
