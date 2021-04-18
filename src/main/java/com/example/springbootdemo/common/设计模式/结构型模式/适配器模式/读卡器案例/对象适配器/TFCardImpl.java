package com.example.springbootdemo.common.设计模式.结构型模式.适配器模式.读卡器案例.对象适配器;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/18 11:44 上午
 * @desrription 目标类
 */
public class TFCardImpl implements TFCard {
    @Override
    public String readTF() {
        String msg = "TFCard read msg : hello world TF";
        return msg;
    }

    @Override
    public void writeTF(String msg) {
        System.out.println("TFCard write msg : " + msg);
    }
}
