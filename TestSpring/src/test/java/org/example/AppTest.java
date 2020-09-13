package org.example;

import com.alibaba.fastjson.JSON;
import org.example.beans.UserService;
import org.example.beans.dao.MyTab;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void appStart()
    {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = (UserService) applicationContext.getBean("userService");
        try {
            userService.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<MyTab> myTabList = userService.queryAll();
        System.out.println(JSON.toJSONString(myTabList, true));
        applicationContext.close();
    }

    @Test
    public void jdkProxy() {

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        };
    }
}
