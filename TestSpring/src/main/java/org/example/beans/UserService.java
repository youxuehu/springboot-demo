package org.example.beans;

import org.example.beans.dao.MyTab;

import java.util.List;

public interface UserService {

    void update();

    List<MyTab> queryAll();
}
