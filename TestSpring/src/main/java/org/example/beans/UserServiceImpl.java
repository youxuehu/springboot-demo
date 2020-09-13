package org.example.beans;

import org.example.aop.Log;
import org.example.beans.dao.MyTab;
import org.example.beans.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public void init() {
        System.out.println("<<<<<<<<<< UserServiceImpl init ~~ >>>>>>>>>>> ");
    }
    public void destroy() {
        System.out.println("<<<<<<<<<< UserServiceImpl destroy ~~ >>>>>>>>>>> ");
    }
    public UserServiceImpl() {
        System.out.println("<<<<<<<<<< UserServiceImpl create ~~ >>>>>>>>>>> ");
    }

    @Override
    public void update() {
        userDAO.update();
    }

    @Override
    @Log(title = "queryALl", function = "查询所有用户信息列表")
    public List<MyTab> queryAll() {
        return userDAO.queryAll();
    }

    @Override
    public String sayHello(String name) {
        return name + ", say hello";
    }

    public void setUserDAO(UserDAO userDAO) {
        System.out.println("<<<<<<<<<< UserServiceImpl setUserDAO ~~ >>>>>>>>>>> ");
        this.userDAO = userDAO;
    }
}
