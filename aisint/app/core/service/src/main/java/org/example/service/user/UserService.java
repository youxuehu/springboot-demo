package org.example.service.user;

import org.example.db.user.model.UserInfo;

import java.util.List;

public interface UserService {

    List<UserInfo> queryList();

    Integer insert(UserInfo userInfo);

}
