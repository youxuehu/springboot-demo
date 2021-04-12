package com.example.springbootdemo.common.设计模式.工厂.demo1.factory.method;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 9:59 下午
 * @desrription 这是类的描述信息
 */
public class LatteCoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
