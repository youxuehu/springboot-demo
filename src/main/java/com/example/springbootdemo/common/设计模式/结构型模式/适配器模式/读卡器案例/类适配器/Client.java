package com.example.springbootdemo.common.设计模式.结构型模式.适配器模式.读卡器案例.类适配器;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/18 11:47 上午
 * @desrription 测试【类适配器】模式,电脑通过读卡器读取TF卡中的数据
 */
public class Client {
    public static void main(String[] args) {
        Computer computer = new Computer();
        String msg = computer.readSD(new SDCardImpl());
        System.out.println(msg);
        System.out.println("===================");

        String msg1 = computer.readSD(new SDAdapterTF());
        System.out.println(msg1);
    }
}
