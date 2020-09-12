package com.example.springbootdemo.common.socket.rpc.ref;

public class UserServiceImpl implements UserService {
    @Override
    public String queryById(Integer id) {
        return "I`m jack";
    }
}
