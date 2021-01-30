package com.example.springbootdemo.common.db.service.impl;

import com.example.springbootdemo.common.aop.Log;
import com.example.springbootdemo.common.db.dao.quartz.jobandtrigger.mapper.JobAndTriggerMapper;
import com.example.springbootdemo.common.db.dao.quartz.jobandtrigger.model.JobAndTrigger;
import com.example.springbootdemo.common.db.service.IJobAndTriggerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {

	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;

	@Log(value = "JobAndTriggerImpl getJobAndTriggerDetails")
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(String keyword, Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails(keyword);
		PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
		return page;
	}

}