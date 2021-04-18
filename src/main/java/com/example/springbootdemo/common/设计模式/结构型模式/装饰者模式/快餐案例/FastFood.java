package com.example.springbootdemo.common.设计模式.结构型模式.装饰者模式.快餐案例;

/**
 * @author youxuehu
 * @version v1.0
 * @className FastFood
 * @date 2021/4/18 6:41 下午
 * @desrription 抽象角色
 */
public abstract class FastFood {
    private float price;
    private String desc;

    public FastFood(float price, String desc) {
        this.price = price;
        this.desc = desc;
    }

    public FastFood() {
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public abstract float cost();
}
