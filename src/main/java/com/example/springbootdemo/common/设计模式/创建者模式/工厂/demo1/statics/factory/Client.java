package com.example.springbootdemo.common.设计模式.创建者模式.工厂.demo1.statics.factory;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 9:31 下午
 * @desrription
 */
public class Client {
    public static void main(String[] args) {
        CoffeeStore store = new CoffeeStore();
        Coffee coffee = store.orderCoffee("american");
        String name = coffee.getName();
        System.out.println(name);
    }
}
