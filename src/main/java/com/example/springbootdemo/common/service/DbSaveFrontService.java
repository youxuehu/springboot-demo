package com.example.springbootdemo.common.service;

import com.example.springbootdemo.common.db.dao.dbsavefront.model.DbSaveFront;

import java.util.List;

public interface DbSaveFrontService {
    void insertOrUpdate(String keyString, String valueString);
    String queryValueByKeyString(String keyString);
    DbSaveFront queryByKeyString(String keyString);
    List<DbSaveFront> queryLikeKeyword(String keyword, Integer pageIndex, Integer pageSize);
    boolean checkExists(String keyString);
    void deleteByKeyString(String keyString);
    Integer queryCountByKeyWord(String keyword);
}