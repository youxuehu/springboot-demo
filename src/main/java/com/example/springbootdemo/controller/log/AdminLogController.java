package com.example.springbootdemo.controller.log;

import com.example.common.db.dao.adminoperatelog.mapper.AdminOperateLogMapper;
import com.example.common.db.dao.adminoperatelog.model.AdminOperateLog;
import com.example.common.db.dao.adminoperatelog.model.AdminOperateLogExample;
import com.example.springbootdemo.controller.params.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/log")
public class AdminLogController {

    @Autowired
    AdminOperateLogMapper adminOperateLogMapper;

    @RequestMapping("get")
    @ResponseBody
    public MyResult<List<AdminOperateLog>> get(Integer pageIndex, Integer pageSize) {
        MyResult<List<AdminOperateLog>> result = new MyResult();
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        AdminOperateLogExample condition = new AdminOperateLogExample();
        int total = adminOperateLogMapper.countByExample(condition);
        int offSet = pageIndex == 1 ? pageSize * (pageIndex - 1) : pageSize * (pageIndex - 1) + 1;
        condition.setLimitStart(offSet);
        condition.setLimitEnd(pageSize);
        condition.setOrderByClause("id desc");
        List<AdminOperateLog> adminOperateLogs = adminOperateLogMapper.selectByExampleWithBLOBs(condition);
        result.setTotal(total);
        result.setPageIndex(pageIndex);
        result.setPageSize(pageSize);
        result.success(adminOperateLogs);
        return result;
    }
}
