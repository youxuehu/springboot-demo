package com.example.springbootdemo.common.设计模式.工厂.demo1.abstracts.factory;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 10:27 下午
 * @desrription 这是类的描述信息
 */
public class Client {
    public static void main(String[] args) {
        DessertFactory factory = new AmericanDessertFactory();
        Coffee coffee = factory.createCoffee();
        Dessert dessert = factory.createDessert();
        System.out.println(coffee.getName());
        System.out.println(dessert.show());
    }
}
