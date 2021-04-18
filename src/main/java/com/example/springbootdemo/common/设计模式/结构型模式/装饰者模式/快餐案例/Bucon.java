package com.example.springbootdemo.common.设计模式.结构型模式.装饰者模式.快餐案例;

/**
 * @author youxuehu
 * @version v1.0
 * @className Egg
 * @date 2021/4/18 6:50 下午
 * @desrription 培根类 （具体的装饰者）
 */
public class Bucon extends Garnish {


    public Bucon(FastFood fastFood) {
        super(fastFood, 2, "培根");
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
