package org.example.beans;

import org.example.beans.dao.MyTab;
import org.example.beans.dao.UserDAO;

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
    public List<MyTab> queryAll() {
        return userDAO.queryAll();
    }

    public void setUserDAO(UserDAO userDAO) {
        System.out.println("<<<<<<<<<< UserServiceImpl setUserDAO ~~ >>>>>>>>>>> ");
        this.userDAO = userDAO;
    }
}
