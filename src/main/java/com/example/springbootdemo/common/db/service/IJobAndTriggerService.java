package com.example.springbootdemo.common.db.service;

import com.example.springbootdemo.common.db.dao.quartz.jobandtrigger.model.JobAndTrigger;
import com.github.pagehelper.PageInfo;

public interface IJobAndTriggerService {
	PageInfo<JobAndTrigger> getJobAndTriggerDetails(String keyword, Integer pageNum, Integer pageSize);
}
