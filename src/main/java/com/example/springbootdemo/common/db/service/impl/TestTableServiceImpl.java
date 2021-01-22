package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.aop.Log;
import com.example.springbootdemo.common.db.dao.testtable.mapper.TestTableMapper;
import com.example.springbootdemo.common.db.dao.testtable.model.TestTable;
import com.example.springbootdemo.common.db.dao.testtable.model.TestTableExample;
import com.example.springbootdemo.common.db.service.TestTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@CacheConfig(cacheNames = "testtable")
public class TestTableServiceImpl implements TestTableService {

    @Autowired
    private TestTableMapper testTableMapper;

    @Override
    @Log(value = "TestTableServiceImpl queryTestTableByKeyword")
    @Cacheable(cacheNames = "testtable", key = "#keyword")
    public PageInfo<TestTable> queryTestTableByKeyword(String keyword, Integer pageSize, Integer pageIndex) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageIndex, pageSize);
        TestTableExample testTableExample = new TestTableExample();
        TestTableExample.Criteria criteria = testTableExample.createCriteria();
        TestTableExample.Criteria criteria1 = testTableExample.or();
        TestTableExample.Criteria criteria2 = testTableExample.or();
        if (StringUtils.isNotBlank(keyword)) {
            criteria.andTestNameLike("%"+keyword+"%");
            criteria1.andTestNameLike("%"+keyword+"%");
            criteria2.andTestNameLike("%"+keyword+"%");
        }

        List<TestTable> testTables = testTableMapper.selectByExample(testTableExample);
        return new PageInfo(testTables);
    }

    @Override
    @Log(value = "TestTableServiceImpl queryById")
    public TestTable queryById(Long testId) {
        return testTableMapper.selectByPrimaryKey(testId);
    }

    @Override
    @Log(value = "TestTableServiceImpl queryByName")
    public TestTable queryByName(String testName) {
        TestTableExample condition = new TestTableExample();
        condition.createCriteria().andTestNameEqualTo(testName);
        List<TestTable> testTables = testTableMapper.selectByExample(condition);
        return CollectionUtils.isEmpty(testTables) ? null : testTables.get(0);
    }

    @Override
    @Log(value = "TestTableServiceImpl insertTestTable")
    @CachePut(key = "#testTable.testName")
    public TestTable insertTestTable(TestTable testTable) {
        Integer id = testTableMapper.insertSelective(testTable);
        testTable.setTestId(id.longValue());
        return testTable;
    }

    @Override
    @CachePut(key = "#testTable.testId")
    @Log(value = "TestTableServiceImpl updateTestTable")
    public TestTable updateTestTable(TestTable testTable) {
        testTableMapper.updateByPrimaryKeySelective(testTable);
        return testTable;
    }

    // 删除数据时删除缓存的value
    @Override
    @Log(value = "TestTableServiceImpl deleteTestTable")
    @CacheEvict(key = "#testId", allEntries = true) // allEntries = true 删除数据时删除缓存的key和value
    public void deleteTestTable(Long testId) {
        testTableMapper.deleteByPrimaryKey(testId);
    }
}
