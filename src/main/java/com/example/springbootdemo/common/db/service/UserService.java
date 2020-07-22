package com.example.springbootdemo.common.db.service;

import com.example.springbootdemo.common.db.dao.user.model.User;

import java.util.List;

public interface UserService {

    int insertUser(User user);

    List<User> queryList();

    int updateUser(User user);

    int deleteUser(Integer id);
}
