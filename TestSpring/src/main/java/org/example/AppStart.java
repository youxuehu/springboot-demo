package org.example;

import com.alibaba.fastjson.JSON;
import org.example.beans.UserService;
import org.example.beans.dao.MyTab;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AppStart {
    public static void main(String[] args) {
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
}
