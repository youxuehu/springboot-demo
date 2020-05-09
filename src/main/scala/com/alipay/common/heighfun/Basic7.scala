package com.alipay.common.heighfun

/**
 * 高阶函数
 */
class Basic7 {

}
object Basic7 extends App {
  // List
  val list = List(1,2,3,4,5,6,7,8,9,10)
  println(list)
  // 写法1
  val newList = list.map((x : Int) => 2 * x)
  println(newList)
  //写法2
  var nn = list.map(x => x * 3)
  println(nn)
  //写法3
  val cc = list.map( 2 * _)
  println(cc)
  //写法4
  list.map(item => {
    println(item * 2)
  })
  // 写法5
  val ff = list.foreach(f => {
    println(3 * f)
  })


  // Set 元素不能重复
  val s = Set(1,2,1,2,3,3,3,3)
  //Set(1, 2, 3)
  println(s)

  // 元组
  val hostPort = ("localhost","8080")
  println(hostPort)
  // 访问元素
  println(hostPort._1)
  println(hostPort._2)
  //字典
  val map = Map("name"-> "zhangsan", "age"-> 20)
  println(map)
  // 获取元素内容
  println(map.get("name1"))

  //配对 zip
  val a = List(1,2,3)
  val b = List(4,5,6)
  val zip = a.zip(b)
  // 输出 List((1,4), (2,5), (3,6))
  println(zip)

  // 过滤  filter
  var f1 = List(1,2,3,4,5)
  // 过滤 奇数
  val f2 = f1.filter(item =>  item % 2 == 0)
  // 输出 List(2, 4)
  println(f2)

  // 拆分 partition
  val p = List(1,2,3,4,5,6,7)
  val p2 = p.partition(_ % 2 ==0)
  // (List(2, 4, 6),List(1, 3, 5, 7))
  println(p2)

  //合并List,将多个list合并成一个list
  val fn = List(1,2,3)
  val fm = List(4,5,6)
  val ftnList = List(fn,fm).flatten
  // 输出  List(1, 2, 3, 4, 5, 6)
  println(ftnList)

  // 合并list ， 并修改元素的值
  val flatList = List(fn,fm)
  // 输出 List(2, 4, 6, 8, 10, 12) ，  2倍输出
  val newFlatList = flatList.flatMap(x => x.map(_ * 2))
  println(newFlatList)

}