package org.example.db.dao.cleantask.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.db.dao.cleantask.model.CleanTask;
import org.example.db.dao.cleantask.model.CleanTaskExample;

public interface CleanTaskMapper {
    long countByExample(CleanTaskExample example);

    int deleteByExample(CleanTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CleanTask record);

    int insertSelective(CleanTask record);

    List<CleanTask> selectByExample(CleanTaskExample example);

    CleanTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CleanTask record, @Param("example") CleanTaskExample example);

    int updateByExample(@Param("record") CleanTask record, @Param("example") CleanTaskExample example);

    int updateByPrimaryKeySelective(CleanTask record);

    int updateByPrimaryKey(CleanTask record);
}