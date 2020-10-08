package com.example.springbootdemo.common.db.service.quartz.impl;

import com.example.springbootdemo.common.db.dao.quartz.jobandtrigger.mapper.JobAndTriggerMapper;
import com.example.springbootdemo.common.db.dao.quartz.jobandtrigger.model.JobAndTrigger;
import com.example.springbootdemo.common.db.service.quartz.IJobAndTriggerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {

	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;
	
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
		PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
		return page;
	}

}