package com.example.springbootdemo.common.设计模式.结构型模式.装饰者模式.快餐案例;

/**
 * @author youxuehu
 * @version v1.0
 * @className Egg
 * @date 2021/4/18 6:50 下午
 * @desrription 鸡蛋类 （具体的装饰者）
 */
public class Egg extends Garnish {


    public Egg(FastFood fastFood) {
        super(fastFood, 1, "鸡蛋");
    }

    @Override
    public float cost() {
        return getPrice() + getFastFood().cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }
}
