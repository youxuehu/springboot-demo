package com.example.springbootdemo.common.设计模式.单例.demo3;

/**
 * @author youxuehu
 * @version v1.0
 * @date 9:28 下午
 * @desrription 饿汉式 非线程安全
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton singleton;

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
