package com.example.springbootdemo.common.设计模式.工厂.demo1.statics.factory;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 9:29 下午
 * @desrription 工厂模式： 每次都要new一个对象，
 */
public class CoffeeStore {

    public Coffee orderCoffee(String type) {
        Coffee coffee = SimpleCoffeeFactory.createCoffee(type);
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }
}
