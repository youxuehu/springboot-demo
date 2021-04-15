package com.example.springbootdemo.common.设计模式.创建者模式.工厂.demo1.abstracts.factory;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 10:23 下午
 * @desrription 抽象工厂： 生产统一类型的产品
 */
public interface DessertFactory {

    Dessert createDessert();

    Coffee createCoffee();
}
