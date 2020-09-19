package org.example.beans;

import org.example.beans.dao.MyTab;

import java.util.List;

public interface UserService {

    void update();

    List<MyTab> queryAll();

    String sayHello(String name);

    default String testMethod() {
        return "hello world";
    }

    static String staticMethod() {
        return "static method";
    }

    String FLAG = "FLAG";


}