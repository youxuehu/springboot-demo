package com.alipay.common.base

class Basic2 {

}
//class Person{
//  //变量值，默认不赋值 // 会生成getter和setter方法
//  var name : String = _
//  //只会生成getter方法
//  var age = 10
//  private[this] val gender = "male"
//}
//主构造器直接跟在类名后面，主构造器中的参数最后会被编译成字段
//主构造器执行的时候，会执行类中所有的代码
//参数声明时不带val和var，那么就相当于private[this]!!!
class Person(val name: String, val age : Int){
  println("This is the primary constructor")
  var gender : String = _
  //附属构造器名称为this
  //每一个附属构造器必须首先调用已经存在的构造器或者附属构造器
  def this(name:String, age :Int, gender : String){
    this(name, age)
    this.gender = gender
  }
  val school = "dfd"

}


class Student(name:String, age : Int, val major: String) extends Person(name,age){
  println("this is the subclass of Person, major is : " + major)
  override val school = "baizhi"
  override def toString: String = "super.toString"
}

object Basic2{
  def main(args: Array[String]): Unit = {
    //括号可以省略
//    var p = new Person
//    p.name = "jack"
//    println(p.name + ":" + p.age)
//
//    val p = new Person("jack", 29, "male")
//    println(p.name + "," + p.age + "," + p.gender)

    var stu = new Student("Jack", 20, "math")
    println(stu.name + "," + stu.age + "," + stu.major)
    println(stu.toString)
  }
}
