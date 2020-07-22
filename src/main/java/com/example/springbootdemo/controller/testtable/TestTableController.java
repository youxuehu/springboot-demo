package com.example.springbootdemo.controller.testtable;

import com.example.springbootdemo.common.db.dao.testtable.model.TestTable;
import com.example.springbootdemo.service.TestTableService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/db")
public class TestTableController {

    @Autowired
    private TestTableService testTableService;

    @RequestMapping("/queryTestTableByKeyword")
    public MyResult<PageInfo<TestTable>> queryTestTableByKeyword(HttpServletRequest request, @RequestParam("keyword") String keyword,
                                                       @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

        MyResult<PageInfo<TestTable>> result = new MyResult<>();
        PageInfo<TestTable> testTables = testTableService.queryTestTableByKeyword(keyword, pageSize, pageIndex);
        result.setData(testTables);
        result.setSuccess(true);
        return result;
    }
}
