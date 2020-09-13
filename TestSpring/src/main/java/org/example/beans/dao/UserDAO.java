package org.example.beans.dao;

import java.util.List;

public interface UserDAO {

    void update();

    List<MyTab> queryAll();
}
