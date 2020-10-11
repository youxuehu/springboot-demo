package org.example;

public class UserServiceImpl implements UserService {
    @Override
    public String sayHi(String name) {
        return "hello, " + name;
    }
}
