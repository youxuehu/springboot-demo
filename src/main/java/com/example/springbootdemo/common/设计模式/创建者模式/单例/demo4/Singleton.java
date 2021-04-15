package com.example.springbootdemo.common.设计模式.创建者模式.单例.demo4;

/**
 * @author youxuehu
 * @version v1.0
 * @date 9:28 下午
 * @desrription 饿汉式 线程安全; 性能问题
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton singleton;

    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
