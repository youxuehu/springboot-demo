package com.example.springbootdemo.common.设计模式.创建者模式.单例.demo9;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/11 9:38 下午
 * @desrription 懒汉式： 静态内部类
 */
public class Singleton {

    private static AtomicBoolean flag = new AtomicBoolean(false);

    private Singleton() {
        if (flag.getAndSet(true)) {
            throw new RuntimeException("不能创建多个对象");
        }
    }

    private static class SingletonHolder {
        private static final Singleton SINGLETON = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.SINGLETON;
    }
}
