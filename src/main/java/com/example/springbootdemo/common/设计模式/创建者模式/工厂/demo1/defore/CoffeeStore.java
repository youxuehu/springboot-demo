package com.example.springbootdemo.common.设计模式.创建者模式.工厂.demo1.defore;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 9:29 下午
 * @desrription 工厂模式： 每次都要new一个对象，
 */
public class CoffeeStore {

    public Coffee orderCoffee(String type) {
        Coffee coffee = null;

        if ("american".equals(type)) {
            coffee = new AmericanCoffee();
        } else if ("latte".equals(type)) {
            coffee = new LatteCoffee();
        } else {
            throw new RuntimeException("对不起，你点的咖啡不存在");
        }
        coffee.addMilk();
        coffee.addSugar();

        return coffee;
    }
}
