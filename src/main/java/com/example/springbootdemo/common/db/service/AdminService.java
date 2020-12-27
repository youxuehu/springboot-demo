package com.example.springbootdemo.common.db.service;

import com.example.springbootdemo.common.db.dao.admin.model.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> queryAdmins();
}
