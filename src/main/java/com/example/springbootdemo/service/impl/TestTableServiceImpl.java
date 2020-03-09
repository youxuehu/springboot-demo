package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.common.db.dao.testtable.mapper.TestTableMapper;
import com.example.springbootdemo.common.db.dao.testtable.model.TestTable;
import com.example.springbootdemo.common.db.dao.testtable.model.TestTableExample;
import com.example.springbootdemo.service.TestTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestTableServiceImpl implements TestTableService {

    @Autowired
    private TestTableMapper testTableMapper;

    @Override
    public PageInfo<TestTable> queryTestTableByKeyword(String keyword, Integer pageSize, Integer pageIndex) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageIndex, pageSize);
        TestTableExample testTableExample = new TestTableExample();
        if (StringUtils.isNotBlank(keyword)) {
            testTableExample.createCriteria().andTestNameLike(keyword);
        }
        List<TestTable> testTables = testTableMapper.selectByExample(testTableExample);
        return new PageInfo<TestTable>(testTables);
    }
}
