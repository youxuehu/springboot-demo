package com.example.springbootdemo.common.设计模式.单例;

/**
 * 懒汉式：调用的时候创建
 */
public class SingletonTest {
    private SingletonTest() {}
    private static SingletonTest singletonTest = null;
    public static SingletonTest getInstance() {
        return InnerClass.singletonTest;
    }
    private static class InnerClass {
        static SingletonTest singletonTest = new SingletonTest();
    }
}
