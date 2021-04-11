package com.example.springbootdemo.common.设计模式.单例.demo8;

import java.io.Serializable;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/11 9:38 下午
 * @desrription 懒汉式： 静态内部类
 */
public class Singleton implements Serializable {

    private Singleton() {}

    private static class SingletonHolder {
        private static final Singleton SINGLETON = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.SINGLETON;
    }

    public Object readResolve() {
        return SingletonHolder.SINGLETON;
    }
}
