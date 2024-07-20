package com.example.springbootdemo

/**
 * @version v1.0
 * @className TestMethod
 * @author youxuehu
 * @date 2024/7/20 下午10:30
 * @desrription 这是类的描述信息
 */
class TestMethod {



}

object TestMethod {
  def main(args: Array[String]) {
    println( "muliplier(1) value = " +  multiplier(1) )
    println( "muliplier(2) value = " +  multiplier(2) )
  }
  var factor = 3
  val multiplier = (i:Int) => i * factor
}