package com.example.springbootdemo.common.db.service.quartz;

import com.example.springbootdemo.common.db.dao.quartz.jobandtrigger.model.JobAndTrigger;
import com.github.pagehelper.PageInfo;

public interface IJobAndTriggerService {
	PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
