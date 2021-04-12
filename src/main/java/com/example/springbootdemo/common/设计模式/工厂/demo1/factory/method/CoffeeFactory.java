package com.example.springbootdemo.common.设计模式.工厂.demo1.factory.method;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 9:58 下午
 * @desrription 工厂方法；
 *          缺点：增加了系统的复杂度，如果后期需要添加一个产品，则需要添加一个工厂类
 */
public interface CoffeeFactory {

    Coffee createCoffee();
}
