package com.example.springbootdemo.common.设计模式.单例;

/**
 * 饿汉式：启动时就创建
 */
public class SingletonHangryTest {
    private SingletonHangryTest() {}
    private static final SingletonHangryTest singletonTest = new SingletonHangryTest();
    public static SingletonHangryTest getInstance() {
        return singletonTest;
    }
}
