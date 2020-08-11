package com.example.springbootdemo.abstractTest

class Basic3 {

}

abstract class Person1{
  def spark
  val name: String
  var age : Int
}

class  Student1 extends Person1{
  def spark: Unit = {
    println("spark!!!")
  }
  val name = "AAA"
  var age = 30
}

//trait Logger{
//  def log(msg: String): Unit ={
//    println("log "+ msg)
//  }
//}
//
//class Test extends Logger{
//  def test: Unit ={
//    log("xxx")
//  }
//}



//trait Logger{
//  def log(msg : String)
//}
//trait  ConsoleLogger extends Logger{
//  override def log(msg: String): Unit = {
//    println(msg)
//  }
//}
//class Test extends ConsoleLogger{
//  def test: Unit ={
//    log("BBB")
//  }
//}



trait ConsoleLogger{
  def log(msg : String): Unit ={
    println("save money : " + msg)
  }
}

trait MessageConsoleLogger extends ConsoleLogger{
  override def log(msg: String): Unit = {
    println("save money to bank : " + msg)
  }
}

abstract class Account {
  def save()
}

class MyAccount extends Account with ConsoleLogger{
  override def save(): Unit = {
    log("100")
  }
}


object Basic3 extends App{
//  val acc = new MyAccount
//  acc.save()

  val acc = new MyAccount with MessageConsoleLogger
  acc.save()
//  val s = new Student1
//  s.spark
//
//  println(s.name + ":" + s.age)

//  val t = new Test
//  t.test
}