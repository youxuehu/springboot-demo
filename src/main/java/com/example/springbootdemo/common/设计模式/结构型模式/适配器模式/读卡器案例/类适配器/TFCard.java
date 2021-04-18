package com.example.springbootdemo.common.设计模式.结构型模式.适配器模式.读卡器案例.类适配器;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/18 11:43 上午
 * @desrription 目标
 */
public interface TFCard {
    String readTF();
    void writeTF(String msg);
}
