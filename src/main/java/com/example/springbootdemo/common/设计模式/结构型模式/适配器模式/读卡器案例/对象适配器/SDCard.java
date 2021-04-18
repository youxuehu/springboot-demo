package com.example.springbootdemo.common.设计模式.结构型模式.适配器模式.读卡器案例.对象适配器;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/18 11:41 上午
 * @desrription 适配者
 */
public interface SDCard {
    String readSD();
    void writeSD(String msg);
}
