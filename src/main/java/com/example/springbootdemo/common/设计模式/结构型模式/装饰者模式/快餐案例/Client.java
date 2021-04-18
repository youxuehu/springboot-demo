package com.example.springbootdemo.common.设计模式.结构型模式.装饰者模式.快餐案例;

/**
 * @author youxuehu
 * @version v1.0
 * @className Client
 * @date 2021/4/18 6:54 下午
 * @desrription 装饰者模式
 */
public class Client {
    public static void main(String[] args) {
        FastFood fastFood = new FriedRice();
        System.out.println(fastFood.getDesc() + "  " + fastFood.cost() + "元");

        fastFood = new Egg(fastFood);
        System.out.println(fastFood.getDesc() + "  " + fastFood.cost() + "元");
        fastFood = new Egg(fastFood);
        System.out.println(fastFood.getDesc() + "  " + fastFood.cost() + "元");
        fastFood = new Egg(fastFood);
        System.out.println(fastFood.getDesc() + "  " + fastFood.cost() + "元");
    }
}
