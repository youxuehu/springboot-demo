package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.db.dao.userinfos.mapper.UserinfosMapper;
import com.example.springbootdemo.common.db.dao.userinfos.model.Userinfos;
import com.example.springbootdemo.common.db.service.UserinfosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserinfosServiceImpl implements UserinfosService {

    static final Logger LOG = LoggerFactory.getLogger(UserinfosServiceImpl.class);

    @Autowired
    UserinfosMapper userinfosMapper;

    @Override
    public List<Integer> insertUserinfos() {
        LOG.info("insert into table userinfos .....");
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Userinfos userinfos = new Userinfos();
            userinfos.setUsername("zhangsan" + i);
            userinfos.setNickname("jask" + i);
            userinfos.setAge(i);
            userinfos.setEmail("zhangsan@hotmail.com");
            userinfos.setGmtCreate(new Date());
            userinfos.setIsDelete(false);
            Integer id = userinfosMapper.insertSelective(userinfos);
            ids.add(id);
        }
        return ids;
    }
}
