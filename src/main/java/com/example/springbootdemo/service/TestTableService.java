package com.example.springbootdemo.service;

import com.example.springbootdemo.common.db.dao.testtable.model.TestTable;
import com.github.pagehelper.PageInfo;

public interface TestTableService {

    PageInfo<TestTable> queryTestTableByKeyword(String keyword, Integer pageSize, Integer pageIndex);
}
