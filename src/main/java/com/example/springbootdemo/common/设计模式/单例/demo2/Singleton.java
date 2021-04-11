package com.example.springbootdemo.common.设计模式.单例.demo2;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/11
 * @desrription 懒汉式 静态代码快
 */
public class Singleton {

    private static Singleton singleton;

    private Singleton() {}

    static {
        singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return singleton;
    }
}
