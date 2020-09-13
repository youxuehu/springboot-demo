package org.example;

import com.alibaba.fastjson.JSON;
import org.example.beans.UserService;
import org.example.beans.UserServiceImpl;
import org.example.beans.dao.MyTab;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class TestAOP {

    private UserService proxyInstance;

    /**
     * JDK 动态代理实现逻辑
     */
    @Test
    public void testJDKProxy() {
        // target目标
        UserService userService = new UserServiceImpl();
        // 额外功能
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = method.invoke(userService, args);
                return result;
            }
        };

        proxyInstance = (UserService) Proxy.newProxyInstance(
                                                                    ClassLoader.getSystemClassLoader(),
                                                                    userService.getClass().getInterfaces(),
                                                                    invocationHandler);
        System.out.println(proxyInstance.testMethod());
        System.out.println(proxyInstance.getClass());
        System.out.println(proxyInstance.FLAG);
    }


    @Test
    public void testSpringAOPProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = (UserService) applicationContext.getBean("userService");
        List<MyTab> myTabList = userService.queryAll();
        System.out.println(JSON.toJSONString(myTabList, true));
        String testMethod = userService.testMethod();
        System.out.println(testMethod);

    }
}
