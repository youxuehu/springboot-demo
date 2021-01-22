package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.aop.Log;
import com.example.springbootdemo.common.db.dao.user.mapper.UserMapper;
import com.example.springbootdemo.common.db.dao.user.model.User;
import com.example.springbootdemo.common.db.dao.user.model.UserExample;
import com.example.springbootdemo.common.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Log(value = "UserServiceImpl insertUser")
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    @Log(value = "UserServiceImpl queryList")
    public List<User> queryList() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    @Log(value = "UserServiceImpl updateUser")
    public int updateUser(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(user.getId());
        return userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    @Log(value = "UserServiceImpl deleteUser")
    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
