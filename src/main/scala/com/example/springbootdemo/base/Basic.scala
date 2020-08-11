package com.example.springbootdemo.base

class Basic {

}

object Basic{

  def add(a : Int, b : Int) : Int = {
    return a + b
  }
  def main(args: Array[String]): Unit ={
    val sum = add(10, 20)
    println(sum)
    for (i <- 1 until(10) if i % 2 ==0){
      println(i)
    }
  }
}
