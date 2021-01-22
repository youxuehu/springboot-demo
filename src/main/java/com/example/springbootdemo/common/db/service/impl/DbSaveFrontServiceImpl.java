package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.db.dao.dbsavefront.mapper.DbSaveFrontMapper;
import com.example.springbootdemo.common.db.dao.dbsavefront.model.DbSaveFront;
import com.example.springbootdemo.common.db.dao.dbsavefront.model.DbSaveFrontExample;
import com.example.springbootdemo.common.db.service.DbSaveFrontService;
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
    public List<DbSaveFront> queryLikeKeyword(String keyword, Integer pageIndex, Integer pageSize) {
        DbSaveFrontExample condition = new DbSaveFrontExample();
        DbSaveFrontExample.Criteria criteria = condition.createCriteria();
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (StringUtils.isNotBlank(keyword)) {
            criteria.andKeyStringLike("%" + keyword + "%");
        }
        // 分页
        int offSet = pageIndex == 1 ? pageSize * (pageIndex - 1) : pageSize * (pageIndex - 1) + 1;
        condition.setLimitStart(offSet);
        condition.setLimitEnd(pageSize);
        List<DbSaveFront> dbSaveFronts = dbSaveFrontMapper.selectByExample(condition);
        return CollectionUtils.isEmpty(dbSaveFronts) ? null : dbSaveFronts;
    }

    @Override
    public boolean checkExists(String keyString) {
        String valueString = queryValueByKeyString(keyString);
        return StringUtils.isBlank(valueString) ? false : true;
    }

    @Override
    public void deleteByKeyString(String keyString) {
        DbSaveFrontExample condition = new DbSaveFrontExample();
        DbSaveFrontExample.Criteria criteria = condition.createCriteria();
        criteria.andKeyStringEqualTo(keyString);
        dbSaveFrontMapper.deleteByExample(condition);
    }

    @Override
    public Integer queryCountByKeyWord(String keyword) {
        DbSaveFrontExample condition = new DbSaveFrontExample();
        DbSaveFrontExample.Criteria criteria = condition.createCriteria();
        if (StringUtils.isNotBlank(keyword)) {
            criteria.andKeyStringLike("%" + keyword + "%");
        }
        return dbSaveFrontMapper.countByExample(condition);
    }
}
