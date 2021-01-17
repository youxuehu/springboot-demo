package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.db.dao.dbsavefront.model.DbSaveFront;
import com.example.springbootdemo.common.service.DbSaveFrontService;
import com.example.springbootdemo.controller.params.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cache/savefront")
public class DbSaveFrontManagerController {

    @Autowired
    DbSaveFrontService dbSaveFrontService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "queryByKeyword")
    public MyResult<List<DbSaveFront>> queryByKeyword(String keyword, Integer pageIndex, Integer pageSize) {
        MyResult<List<DbSaveFront>> myResult = new MyResult();
        List<DbSaveFront> dbSaveFronts = dbSaveFrontService.queryLikeKeyword(keyword, pageIndex, pageSize);
        Integer total = dbSaveFrontService.queryCountByKeyWord(keyword);
        myResult.success(dbSaveFronts);
        myResult.setTotal(total);
        return myResult;
    }

    @RequestMapping(method = {RequestMethod.POST}, value = "insertOrUpdate")
    @ResponseBody
    public MyResult<Boolean> insertOrUpdate(String keyString, String valString) {
        MyResult<Boolean> myResult = new MyResult();
        dbSaveFrontService.insertOrUpdate(keyString, valString);
        return myResult.success();
    }

    @RequestMapping(method = RequestMethod.POST, value = "delete")
    @ResponseBody
    public MyResult<Boolean> delete(String keyString) {
        MyResult<Boolean> myResult = new MyResult();
        dbSaveFrontService.deleteByKeyString(keyString);
        return myResult.success();
    }
}
