package com.alipay.common

import java.text.SimpleDateFormat
import java.util.Date

class DateTest {

}

object DateTest {
  def main(args: Array[String]): Unit = {
    println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
  }
}