package com.example.springbootdemo.init;

import com.example.springbootdemo.common.db.dao.admin.model.Admin;
import com.example.springbootdemo.utils.DBUtil;
import com.example.springbootdemo.utils.MD5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminInitData {
    public static void main(String[] args) throws Exception {
        createAdminUser(10000);
    }

    private static void createAdminUser(int count) throws Exception {
        List<Admin> admins = new ArrayList<>(count);
        for (int i = 0; i< count; i++) {
            Admin admin = new Admin();
            admin.setId(16000000000L + i);
            admin.setEmail("admin" + i + "@hotmail.com");
            admin.setNickName("bajie" + i);
            admin.setUserName("tiger" + i);
            admin.setSalt("youxuehu");
            admin.setRegTime(new Date());
            admin.setPassWord(MD5Util.fromInputToOutput("zhf123..", admin.getSalt()));
            admins.add(admin);
        }
        Connection connect = DBUtil.getConnect();
        String sql = "insert into admin(id,email,nick_name,salt,pass_word,reg_time) values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        for (int i = 0; i < admins.size(); i++) {
            Admin admin = admins.get(i);
            preparedStatement.setLong(1, admin.getId());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getNickName());
            preparedStatement.setString(4, admin.getSalt());
            preparedStatement.setString(5, admin.getPassWord());
            preparedStatement.setTimestamp(6, new Timestamp(admin.getRegTime().getTime()));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
        connect.close();
        System.out.println("insert db finish");

    }
}
