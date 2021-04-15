package com.example.springbootdemo.common.设计模式.创建者模式.工厂.demo1.config.factory;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 9:26 下午
 * @desrription 这是类的描述信息
 */
public abstract class Coffee {

    public abstract String getName();

    public void addSugar() {
        System.out.println("加糖");
    }

    public void addMilk() {
        System.out.println("加奶");
    }
}
