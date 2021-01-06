package com.example.springbootdemo.service;

import com.example.springbootdemo.common.db.dao.testtable.model.TestTable;
import com.github.pagehelper.PageInfo;

public interface TestTableService {

    PageInfo<TestTable> queryTestTableByKeyword(String keyword, Integer pageSize, Integer pageIndex);

    TestTable queryById(Long testId);

    TestTable queryByName(String testName);

    TestTable insertTestTable(TestTable testTable);

    TestTable updateTestTable(TestTable testTable);

    void deleteTestTable(Long testId);

}
