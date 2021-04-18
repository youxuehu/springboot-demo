package com.example.springbootdemo.common.设计模式.结构型模式.适配器模式.读卡器案例.对象适配器;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/18 11:48 上午
 * @desrription 对象适配器类：如果目标没有实现接口
 */
public class SDAdapterTF implements SDCard {

    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("adapter read tf card");
        return tfCard.readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        tfCard.writeTF(msg);
    }
}
