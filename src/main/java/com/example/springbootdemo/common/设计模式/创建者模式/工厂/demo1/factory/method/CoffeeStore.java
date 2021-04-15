package com.example.springbootdemo.common.设计模式.创建者模式.工厂.demo1.factory.method;


/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 9:29 下午
 * @desrription 工厂模式： 每次都要new一个对象，
 */
public class CoffeeStore {

    private CoffeeFactory coffeeFactory;

    public void setCoffeeFactory(CoffeeFactory coffeeFactory) {
        this.coffeeFactory = coffeeFactory;
    }

    public Coffee orderCoffee() {
        Coffee coffee = coffeeFactory.createCoffee();
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }
}
