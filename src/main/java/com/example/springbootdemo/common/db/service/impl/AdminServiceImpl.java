package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.aop.Log;
import com.example.springbootdemo.common.db.dao.admin.mapper.AdminMapper;
import com.example.springbootdemo.common.db.dao.admin.model.Admin;
import com.example.springbootdemo.common.db.dao.admin.model.AdminExample;
import com.example.springbootdemo.common.db.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    @Log(value = "查询admin列表")
    public List<Admin> queryAdmins() {
        List<Admin> admins = adminMapper.selectByExample(new AdminExample());
        return CollectionUtils.isEmpty(admins) ? new ArrayList<>() : admins;
    }

    @Override
    @Log(value = "根据userName查询admin")
    public Admin queryById(String userName) {
        return adminMapper.selectByPrimaryKey(Long.parseLong(userName));
    }
}
