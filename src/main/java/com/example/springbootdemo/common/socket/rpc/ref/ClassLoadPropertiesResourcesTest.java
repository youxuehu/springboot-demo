package com.example.springbootdemo.common.socket.rpc.ref;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class ClassLoadPropertiesResourcesTest {

    public static void main(String[] args) {

        try {
            InputStream inputStream = ClassLoadPropertiesResourcesTest.class.getClassLoader().
                    getResourceAsStream("com/example/springbootdemo/common/socket/rpc/ref/config/config.properties");

            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();

            String className = properties.getProperty("className");
            Class<?> clazz = Class.forName(className);
            System.out.println(clazz.getName());
            // 1: method.invoke方式执行方法
            Object o = clazz.newInstance();
            Method method = clazz.getMethod("queryById", new Class[]{Integer.class});
            Object result = method.invoke(o, new Object[]{1});
            System.out.println(result);
            // 2: convert 直接强制类型转换，获得对象实例，通过对象调用方法
            UserService userService = (UserService) clazz.newInstance();
            String resp = userService.queryById(1);
            System.out.println(resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
