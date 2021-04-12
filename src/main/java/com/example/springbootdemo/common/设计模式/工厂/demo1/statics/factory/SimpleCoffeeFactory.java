package com.example.springbootdemo.common.设计模式.工厂.demo1.statics.factory;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 9:40 下午
 * @desrription 这是类的描述信息
 */
public class SimpleCoffeeFactory {

    public static Coffee createCoffee(String type) {
        Coffee coffee = null;
        if ("american".equals(type)) {
            coffee = new AmericanCoffee();
        } else if ("latte".equals(type)) {
            coffee = new LatteCoffee();
        } else {
            throw new RuntimeException("对不起，你点的咖啡不存在");
        }
        return coffee;
    }
}
