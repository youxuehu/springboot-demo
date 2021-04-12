package com.example.springbootdemo.common.设计模式.工厂.demo1.config.factory;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 10:43 下午
 * @desrription 这是类的描述信息
 */
public class Client {
    public static void main(String[] args) {
        Coffee american = CoffeeFactory.createCoffee("american");
        System.out.println(american);
    }
}
