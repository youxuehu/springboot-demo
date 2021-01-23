package com.example.springbootdemo.controller.user;

import com.example.springbootdemo.common.db.dao.admin.mapper.AdminMapper;
import com.example.springbootdemo.common.db.dao.admin.model.Admin;
import com.example.springbootdemo.common.db.dao.admin.model.AdminExample;
import com.example.springbootdemo.controller.params.MyResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminAjaxController {

    @Autowired
    AdminMapper adminMapper;

    @RequestMapping("get")
    @ResponseBody
    public MyResult<List<Admin>> get(String keyword, Integer pageIndex, Integer pageSize) {
        MyResult<List<Admin>> result = new MyResult();
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        AdminExample condition = new AdminExample();
        AdminExample.Criteria criteria = condition.createCriteria();
        AdminExample.Criteria criteria2 = condition.createCriteria();
        if (StringUtils.isNotBlank(keyword)) {
            criteria.andUserNameLike("%" + keyword + "%");
            criteria2.andNickNameLike("%" + keyword + "%");
        }
        condition.or(criteria2);
        condition.or(criteria);
        int total = adminMapper.countByExample(condition);
        int offSet = pageIndex == 1 ? pageSize * (pageIndex - 1) : pageSize * (pageIndex - 1) + 1;
        condition.setLimitStart(offSet);
        condition.setLimitEnd(pageSize);
        condition.setOrderByClause("id desc");
        List<Admin> admins = adminMapper.selectByExample(condition);
        result.setTotal(total);
        result.setPageIndex(pageIndex);
        result.setPageSize(pageSize);
        result.success(admins);
        return result;
    }
}
