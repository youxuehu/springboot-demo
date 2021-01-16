package com.example.springbootdemo.common.service.impl;

import com.example.springbootdemo.common.db.dao.dbsavefront.mapper.DbSaveFrontMapper;
import com.example.springbootdemo.common.db.dao.dbsavefront.model.DbSaveFront;
import com.example.springbootdemo.common.db.dao.dbsavefront.model.DbSaveFrontExample;
import com.example.springbootdemo.common.service.DbSaveFrontService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.Date;
import java.util.List;

@Service
public class DbSaveFrontServiceImpl implements DbSaveFrontService {

    @Autowired
    DbSaveFrontMapper dbSaveFrontMapper;

    @Override
    public void insertOrUpdate(String keyString, String valueString) {
        DbSaveFront dbSaveFront = queryByKeyString(keyString);
        if (dbSaveFront == null) {
            DbSaveFront saveFront = new DbSaveFront();
            saveFront.setKeyString(keyString);
            saveFront.setValueString(valueString);
            saveFront.setGmtCreate(new Date());
            dbSaveFrontMapper.insertSelective(saveFront);
            return;
        }
        dbSaveFront.setValueString(valueString);
        dbSaveFront.setGmtUpdate(new Date());
        dbSaveFrontMapper.updateByPrimaryKeySelective(dbSaveFront);
    }

    @Override
    public String queryValueByKeyString(String keyString) {
        DbSaveFront saveFront = queryByKeyString(keyString);
        if (saveFront == null) {
            return null;
        }
        return saveFront.getValueString();
    }

    @Override
    public DbSaveFront queryByKeyString(String keyString) {
        DbSaveFrontExample condition = new DbSaveFrontExample();
        condition.createCriteria().andKeyStringEqualTo(keyString);
        List<DbSaveFront> dbSaveFronts = dbSaveFrontMapper.selectByExample(condition);
        return CollectionUtils.isEmpty(dbSaveFronts) ? null : dbSaveFronts.get(0);
    }

    @Override
    public List<DbSaveFront> queryLikeKeyword(String keyword) {
        DbSaveFrontExample condition = new DbSaveFrontExample();
        condition.createCriteria().andKeyStringLike("%" + keyword + "%");
        List<DbSaveFront> dbSaveFronts = dbSaveFrontMapper.selectByExample(condition);
        return CollectionUtils.isEmpty(dbSaveFronts) ? null : dbSaveFronts;
    }

    @Override
    public boolean checkExists(String keyString) {
        String valueString = queryValueByKeyString(keyString);
        return StringUtils.isBlank(valueString) ? false : true;
    }
}
