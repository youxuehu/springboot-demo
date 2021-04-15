package com.example.springbootdemo.common.设计模式.创建者模式.建造者模式.建造共享单车;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/14 10:10 下午
 * @desrription 建造者模式： 建造复杂对象
 */
public class Client {
    public static void main(String[] args) {
        Director director = new Director(new MobileBuilder());
        Bike bike = director.build();
        System.out.println(bike);
    }
}
