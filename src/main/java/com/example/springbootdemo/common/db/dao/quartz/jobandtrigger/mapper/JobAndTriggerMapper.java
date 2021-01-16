package com.example.springbootdemo.common.db.dao.quartz.jobandtrigger.mapper;

import com.example.springbootdemo.common.db.dao.quartz.jobandtrigger.model.JobAndTrigger;
import java.util.List;

public interface JobAndTriggerMapper {
	List<JobAndTrigger> getJobAndTriggerDetails(String keyword);
}
