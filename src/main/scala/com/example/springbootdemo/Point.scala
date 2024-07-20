package com.example.springbootdemo

/**
 * @version v1.0
 * @className TestClass
 * @author youxuehu
 * @date 2024/7/20 下午10:40
 * @desrription 这是类的描述信息
 */
class Point(xc: Int, yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
    println ("x 的坐标点: " + x);
    println ("y 的坐标点: " + y);
  }
}

object Test {
  def main(args: Array[String]) {
    val pt = new Point(10, 20);

    // 移到一个新的位置
    pt.move(10, 10);
  }
}