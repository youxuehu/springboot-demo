package com.example.springbootdemo.user;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.common.db.dao.user.model.User;
import com.example.springbootdemo.common.db.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Test
    public void insertUser() {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            userService.insertUser(user);
        }
    }

    @Test
    public void queryList() {
        List<User> users = userService.queryList();
        log.info(JSON.toJSONString(users, true));
    }
}
