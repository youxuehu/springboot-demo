package org.example.service.user.impl;

import org.example.db.user.mapper.UserInfoMapper;
import org.example.db.user.model.UserInfo;
import org.example.db.user.model.UserInfoExample;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> queryList() {
        return userInfoMapper.selectByExample(new UserInfoExample());
    }

    @Override
    public Integer insert(UserInfo userInfo) {
        return userInfoMapper.insertSelective(userInfo);
    }
}
