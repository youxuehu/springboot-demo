package com.example.springbootdemo.common.设计模式.单例.demo7;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/11 9:41 下午
 * @desrription 饿汉式： 枚举方式; 线程安全，并且只会装载一次
 */
public enum Singleton {

    INSTANCE;

    public static Singleton getInstance() {
        return INSTANCE;
    }

}
