package com.example.springbootdemo.common.db.service;

import com.example.springbootdemo.common.db.dao.odps.model.Odps;
import com.github.pagehelper.PageInfo;

public interface OdpsTableService {

    PageInfo<Odps> queryOdpsTableByKeyword(String keyword, Integer pageSize, Integer pageIndex);

}
