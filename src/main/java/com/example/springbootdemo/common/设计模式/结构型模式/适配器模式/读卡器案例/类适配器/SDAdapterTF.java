package com.example.springbootdemo.common.设计模式.结构型模式.适配器模式.读卡器案例.类适配器;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/18 11:48 上午
 * @desrription 类适配器类：如果目标实现了接口
 */
public class SDAdapterTF extends TFCardImpl implements SDCard {

    @Override
    public String readSD() {
        System.out.println("adapter read tf card");
        return readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        writeTF(msg);
    }
}
