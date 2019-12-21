package com.example.springbootdemo.common.jps.dao;

import com.example.springbootdemo.common.jps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserNameOrEmail(String userName, String email);

    User findByUserName(String userName);

}
