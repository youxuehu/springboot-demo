package com.example.springbootdemo.common.设计模式.单例.demo1;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/11
 * @desrription 懒汉式 成员变量new
 */
public class Singleton {

    private static final Singleton SINGLETON = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return SINGLETON;
    }
}
