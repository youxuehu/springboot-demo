package com.example.springbootdemo.common.设计模式.单例.demo9;

import java.lang.reflect.Constructor;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/11 10:08 下午
 * @desrription 破坏单例： 反射
 *              解决方案：在私有构造方法中加上校验
 */
public class SingletonTest {

    public static void main(String[] args) throws Exception {

        Class clazz = Singleton.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton singleton = (Singleton) clazz.newInstance();
        Singleton singleton2 = (Singleton) clazz.newInstance();
        System.out.println(singleton == singleton2);
    }


}
