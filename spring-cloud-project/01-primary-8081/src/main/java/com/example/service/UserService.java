package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {

    boolean save(User user);

    boolean update(User user);

    void delete(Integer id);

    User getById(Integer id);

    List<User> listAll();
}
