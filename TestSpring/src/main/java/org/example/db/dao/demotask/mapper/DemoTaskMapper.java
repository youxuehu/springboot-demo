package org.example.db.dao.demotask.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.db.dao.demotask.model.DemoTask;
import org.example.db.dao.demotask.model.DemoTaskExample;

public interface DemoTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    int countByExample(DemoTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    int deleteByExample(DemoTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    int insert(DemoTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    int insertSelective(DemoTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    List<DemoTask> selectByExample(DemoTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    DemoTask selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    int updateByExampleSelective(@Param("record") DemoTask record, @Param("example") DemoTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    int updateByExample(@Param("record") DemoTask record, @Param("example") DemoTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    int updateByPrimaryKeySelective(DemoTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_task
     *
     * @mbggenerated Tue Sep 22 20:57:04 CST 2020
     */
    int updateByPrimaryKey(DemoTask record);
}