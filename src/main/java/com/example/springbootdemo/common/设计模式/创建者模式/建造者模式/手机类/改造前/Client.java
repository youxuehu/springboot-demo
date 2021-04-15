package com.example.springbootdemo.common.设计模式.创建者模式.建造者模式.手机类.改造前;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/14 10:20 下午
 * @desrription 改造前：创建手机对象需要按照顺序设置构造参数
 */
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone("Intel", "8G", "金斯顿", "华硕");
        System.out.println(phone);
    }
}
