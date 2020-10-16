package com.example.springbootdemo.common.db.dao.quartz.qrtzschedulerstate.mapper;

import com.example.springbootdemo.common.db.dao.quartz.qrtzschedulerstate.model.QrtzSchedulerState;
import com.example.springbootdemo.common.db.dao.quartz.qrtzschedulerstate.model.QrtzSchedulerStateExample;
import com.example.springbootdemo.common.db.dao.quartz.qrtzschedulerstate.model.QrtzSchedulerStateKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QrtzSchedulerStateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    int countByExample(QrtzSchedulerStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    int deleteByExample(QrtzSchedulerStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    int deleteByPrimaryKey(QrtzSchedulerStateKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    int insert(QrtzSchedulerState record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    int insertSelective(QrtzSchedulerState record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    List<QrtzSchedulerState> selectByExample(QrtzSchedulerStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    QrtzSchedulerState selectByPrimaryKey(QrtzSchedulerStateKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    int updateByExampleSelective(@Param("record") QrtzSchedulerState record, @Param("example") QrtzSchedulerStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    int updateByExample(@Param("record") QrtzSchedulerState record, @Param("example") QrtzSchedulerStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    int updateByPrimaryKeySelective(QrtzSchedulerState record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QRTZ_SCHEDULER_STATE
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    int updateByPrimaryKey(QrtzSchedulerState record);
}