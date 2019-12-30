package com.example.springbootdemo.common.db.service;

import com.example.springbootdemo.common.db.dao.user.model.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

//@Component
public class TestUser implements InitializingBean {
    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<User> users = userService.queryList();
        users.stream().forEach(user -> {
            System.out.println(user.getId());
        });
        if (!CollectionUtils.isEmpty(users)) {
            return;
        }
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            userService.insertUser(user);
        }

    }
}
