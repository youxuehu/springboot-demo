package com.example.springbootdemo.common.db.dao.worker.mapper;

import com.example.springbootdemo.common.db.dao.worker.model.Worker;
import com.example.springbootdemo.common.db.dao.worker.model.WorkerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    int countByExample(WorkerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    int deleteByExample(WorkerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    int insert(Worker record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    int insertSelective(Worker record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    List<Worker> selectByExample(WorkerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    Worker selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    int updateByExampleSelective(@Param("record") Worker record, @Param("example") WorkerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    int updateByExample(@Param("record") Worker record, @Param("example") WorkerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    int updateByPrimaryKeySelective(Worker record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table worker
     *
     * @mbggenerated Wed Jan 27 21:50:20 CST 2021
     */
    int updateByPrimaryKey(Worker record);
}