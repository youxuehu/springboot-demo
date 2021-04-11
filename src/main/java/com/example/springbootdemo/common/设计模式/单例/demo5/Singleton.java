package com.example.springbootdemo.common.设计模式.单例.demo5;

/**
 * @author youxuehu
 * @version v1.0
 * @date 9:28 下午
 * @desrription 饿汉式 双重检查锁模式 线程安全; 性能问题; 缺点可能会出现空指针问题
 *              解决方案： 在成员变量上加上volatile关键字
 */
public class Singleton {

    private Singleton() {
    }

    private static volatile Singleton singleton;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
