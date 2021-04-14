package com.example.springbootdemo.common.设计模式.建造者模式.demo.建造共享单车;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/14 10:03 下午
 * @desrription 这是类的描述信息
 */
public abstract class Builder {

    public Bike bike = new Bike();

    public abstract void createFrame();

    public abstract void createSaddle();

    public abstract Bike buildBike();

}
