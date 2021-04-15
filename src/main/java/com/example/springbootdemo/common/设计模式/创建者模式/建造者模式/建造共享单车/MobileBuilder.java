package com.example.springbootdemo.common.设计模式.创建者模式.建造者模式.建造共享单车;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/14 10:04 下午
 * @desrription 这是类的描述信息
 */
public class MobileBuilder extends Builder {

    @Override
    public void createFrame() {
        bike.setFrame("黄金车架");
    }

    @Override
    public void createSaddle() {
        bike.setSaddle("牛皮车座");
    }

    @Override
    public Bike buildBike() {
        return bike;
    }
}
