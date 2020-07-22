package com.example.springbootdemo;

import com.example.springbootdemo.common.db.dao.user.model.User;
import com.example.springbootdemo.common.db.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        List<User> users = userService.queryList();
        System.out.println(users);
    }

}
