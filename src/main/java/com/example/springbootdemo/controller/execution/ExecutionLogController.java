package com.example.springbootdemo.controller.execution;

import com.example.common.db.dao.executionlog.mapper.ExecutionLogMapper;
import com.example.common.db.dao.executionlog.model.ExecutionLog;
import com.example.common.db.dao.executionlog.model.ExecutionLogExample;
import com.example.springbootdemo.controller.params.MyResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/task/log")
public class ExecutionLogController {

    @Autowired
    ExecutionLogMapper executionLogMapper;

    @RequestMapping("query")
    @ResponseBody
    public MyResult<List<ExecutionLog>> query(String jobId, Integer pageIndex, Integer pageSize) {
        MyResult<List<ExecutionLog>> result = new MyResult();
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        ExecutionLogExample condition = new ExecutionLogExample();
        if (StringUtils.isNotBlank(jobId)) {
            condition.createCriteria().andJobIdEqualTo(jobId);
        }
        int total = executionLogMapper.countByExample(condition);
        int offSet = pageIndex == 1 ? pageSize * (pageIndex - 1) : pageSize * (pageIndex - 1) + 1;
        condition.setLimitStart(offSet);
        condition.setLimitEnd(pageSize);
        condition.setOrderByClause("id ASC");
        List<ExecutionLog> executionLogs = executionLogMapper.selectByExampleWithBLOBs(condition);
        result.setTotal(total);
        result.setPageIndex(pageIndex);
        result.setPageSize(pageSize);
        result.success(executionLogs);
        return result;
    }
}
