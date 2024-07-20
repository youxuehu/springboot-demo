package com.example.springbootdemo

import java.text.SimpleDateFormat
import java.util.Date

import scala.util.Try

class DateTest {
  def m(x: Int) = x + 3
  val f = (x: Int) => x + 3
}

object DateTest {

  def addInt( a:Int, b:Int ) : Int = {
    var sum:Int = 0
    sum = a + b
    return sum
  }

  def main(args: Array[String]): Unit = {
    println("Returned Value : " + addInt(5, 7))
    println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()))

    // 基本类型
    val byteValue: Byte = 127
    val shortValue: Short = 32767
    val intValue: Int = 2147483647
    val longValue: Long = 9223372036854775807L
    val floatValue: Float = 3.14f
    val doubleValue: Double = 3.141592653589793
    val charValue: Char = 'A'
    val stringValue: String = "Hello, Scala!"
    val booleanValue: Boolean = true

    // 集合类型
    val listValue: List[Int] = List(1, 2, 3)
    val setValue: Set[String] = Set("Scala", "Java", "Python")
    val mapValue: Map[String, Int] = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val arrayValue: Array[Int] = Array(4, 5, 6)
    val tupleValue: (Int, String, Boolean) = (42, "Answer", true)
    val optionValue: Option[String] = Some("I am here")
    val eitherValue: Either[String, Int] = Right(42)
    val tryValue: Try[Int] = Try(10 / 2)

    // 特殊类型
    val unitValue: Unit = ()
    val nullValue: String = null
//    val nothingValue: Nothing = throw new RuntimeException("Nothing value")

    // 输出所有值
    println(s"Byte Value: $byteValue")
    println(s"Short Value: $shortValue")
    println(s"Int Value: $intValue")
    println(s"Long Value: $longValue")
    println(s"Float Value: $floatValue")
    println(s"Double Value: $doubleValue")
    println(s"Char Value: $charValue")
    println(s"String Value: $stringValue")
    println(s"Boolean Value: $booleanValue")

    println(s"List Value: $listValue")
    println(s"Set Value: $setValue")
    println(s"Map Value: $mapValue")
    println(s"Array Value: ${arrayValue.mkString(", ")}")
    println(s"Tuple Value: $tupleValue")
    println(s"Option Value: $optionValue")
    println(s"Either Value: $eitherValue")
    println(s"Try Value: $tryValue")

    println(s"Unit Value: $unitValue")
    println(s"Null Value: $nullValue")

    val x = 10

    if (x < 20) {
      println("x 小于 20")
    } else {
      println("x 大于等于 20")
    }
    val a = 10
    // 无限循环
    while (true) {
      println("a 的值为 : " + a)
    }

    var aa = 0;
    // for 循环
    for( aa <- 1 to 10){
      println( "Value of aa: " + aa );
    }

  }
}
