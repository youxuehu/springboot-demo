package com.example.springbootdemo.common.设计模式.创建者模式.建造者模式.建造共享单车;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/14 10:07 下午
 * @desrription 这是类的描述信息
 */
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Bike build() {
        builder.createFrame();
        builder.createSaddle();
        return builder.buildBike();
    }
}
