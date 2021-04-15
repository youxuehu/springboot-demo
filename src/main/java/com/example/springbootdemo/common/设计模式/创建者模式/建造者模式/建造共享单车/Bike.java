package com.example.springbootdemo.common.设计模式.创建者模式.建造者模式.建造共享单车;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/14 10:01 下午
 * @desrription 产品类：单车
 */
public class Bike {
    private String frame;
    private String saddle;

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getSaddle() {
        return saddle;
    }

    public void setSaddle(String saddle) {
        this.saddle = saddle;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "frame='" + frame + '\'' +
                ", saddle='" + saddle + '\'' +
                '}';
    }
}
