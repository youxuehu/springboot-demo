package com.example.springbootdemo.common.设计模式.创建者模式.工厂.demo1.factory.method;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 10:00 下午
 * @desrription 这是类的描述信息
 */
public class Client {
    public static void main(String[] args) {
        CoffeeStore store = new CoffeeStore();
        store.setCoffeeFactory(new AmericanCoffeeFactory());
        Coffee coffee = store.orderCoffee();
        System.out.println(coffee.getName());
    }
}
