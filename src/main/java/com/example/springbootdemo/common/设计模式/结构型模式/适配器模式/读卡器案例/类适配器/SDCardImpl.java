package com.example.springbootdemo.common.设计模式.结构型模式.适配器模式.读卡器案例.类适配器;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/18 11:42 上午
 * @desrription 适配者类
 */
public class SDCardImpl implements SDCard {
    @Override
    public String readSD() {
        String msg = "SDCard read msg : hello world SD";
        return msg;
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("SDCard write msg : " + msg);
    }
}
