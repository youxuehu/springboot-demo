package com.example.springbootdemo.隐

import com.example.springbootdemo.Point

/**
 * @version v1.0
 * @className Test2
 * @author youxuehu
 * @date 2024/7/20 下午10:53
 * @desrription 这是类的描述信息
 */
object Test2 {

  def main(args: Array[String]): Unit = {



    val pt = new Point(10, 20);

    // 移到一个新的位置
    pt.move(10, 10);
  }
}
