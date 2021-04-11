package com.example.springbootdemo.common.设计模式.单例.demo3;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/11
 * @desrription 这是类的描述信息
 */
public class SingletonTest {

    public static void main(String[] args) {

        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
    }
}
