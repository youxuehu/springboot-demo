package com.example.springbootdemo.common.socket.rpc.ref;

import java.lang.reflect.Method;

/**
 * 通过反射创建对象，并调用方法
 */
public class ReflectCallMethodTest {

    public static void main(String[] args) {

        try {
            Class<?> clazz = Class.forName("com.example.springbootdemo.common.socket.rpc.ref.UserServiceImpl");
            Object o = clazz.newInstance();
            String methodName = "queryById";
            Class[] param = new Class[] {Integer.class};
            Method method = clazz.getMethod(methodName, param);
            Object[] paramValues = new Object[] {1};
            Object result = method.invoke(o, paramValues);

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
