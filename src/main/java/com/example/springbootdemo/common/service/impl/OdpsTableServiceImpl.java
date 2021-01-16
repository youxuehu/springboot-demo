package com.example.springbootdemo.common.service.impl;

import com.example.springbootdemo.common.db.dao.odps.mapper.OdpsMapper;
import com.example.springbootdemo.common.db.dao.odps.model.Odps;
import com.example.springbootdemo.common.db.dao.odps.model.OdpsExample;
import com.example.springbootdemo.common.service.OdpsTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OdpsTableServiceImpl implements OdpsTableService {

    @Autowired
    OdpsMapper odpsMapper;

    @Override
    public PageInfo<Odps> queryOdpsTableByKeyword(String keyword, Integer pageSize, Integer pageIndex) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        OdpsExample condition = new OdpsExample();
        OdpsExample.Criteria criteria = condition.createCriteria();
        OdpsExample.Criteria criteria2 = condition.or();
        if (StringUtils.isNotBlank(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            criteria2.andTablenameLike("%" + keyword + "%");
        }
        PageHelper.startPage(pageIndex, pageSize);
        return new PageInfo<Odps>(odpsMapper.selectByExample(condition));
    }

}
