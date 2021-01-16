package com.example.springbootdemo.controller.testtable;

import com.example.springbootdemo.common.db.dao.testtable.model.TestTable;
import com.example.springbootdemo.controller.params.MyResult;
import com.example.springbootdemo.common.service.TestTableService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/db")
public class TestTableController {

    @Autowired
    private TestTableService testTableService;

    @RequestMapping("query")
    @ResponseBody
    public MyResult<PageInfo<TestTable>> queryTestTableByKeyword(HttpServletRequest request,
                                                                 String keyword,
                                                                 Integer pageIndex,
                                                                 Integer pageSize) {
        MyResult<PageInfo<TestTable>> result = new MyResult<>();
        PageInfo<TestTable> testTables = testTableService.queryTestTableByKeyword(keyword, pageSize, pageIndex);
        result.setData(testTables);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("insert")
    @ResponseBody
    public MyResult<TestTable> insert(HttpServletRequest request, String name) {
        MyResult<TestTable> result = new MyResult<>();
        TestTable testTable = new TestTable();
        testTable.setTestName(name);
        testTable.setGmtCreate(new Date());
        result.setData(testTableService.insertTestTable(testTable));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public MyResult<TestTable> update(HttpServletRequest request, Long testId, String name) {
        MyResult<TestTable> result = new MyResult<>();
        TestTable testTable = testTableService.queryById(testId);
        if (testTable == null) {
            throw new RuntimeException("从db查询表不存在, id is " + testId);
        }
        testTable.setTestName(name);
        testTable.setGmtUpdate(new Date());
        result.setData(testTableService.updateTestTable(testTable));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public MyResult<TestTable> delete(HttpServletRequest request, Long testId) {
        MyResult<TestTable> result = new MyResult<>();
        TestTable testTable = testTableService.queryById(testId);
        if (testTable == null) {
            throw new RuntimeException("从db查询表不存在, id is " + testId);
        }
        testTableService.deleteTestTable(testId);
        result.setSuccess(true);
        return result;
    }
}
