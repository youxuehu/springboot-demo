package org.example.db.service.cleantask;

import org.example.db.dao.cleantask.model.CleanTask;

import java.util.List;

public interface CleanTaskService {

    Long queryCount();

    List<CleanTask> queryByPage(Integer limit);

    int deleteByIds(List<Long> ids);
}
