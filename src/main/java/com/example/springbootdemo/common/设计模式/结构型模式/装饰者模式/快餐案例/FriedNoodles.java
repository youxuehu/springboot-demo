package com.example.springbootdemo.common.设计模式.结构型模式.装饰者模式.快餐案例;

/**
 * @author youxuehu
 * @version v1.0
 * @className FriedNoodles
 * @date 2021/4/18 6:44 下午
 * @desrription 炒面（具体的）
 */
public class FriedNoodles extends FastFood {

    public FriedNoodles() {
        super(12, "炒面");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
