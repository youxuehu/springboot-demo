package com.example.springbootdemo.common.db.dao.testtable.mapper;

import com.example.springbootdemo.common.db.dao.testtable.model.TestTable;
import com.example.springbootdemo.common.db.dao.testtable.model.TestTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestTableMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    int countByExample(TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    int deleteByExample(TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    int deleteByPrimaryKey(Long testId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    int insert(TestTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    int insertSelective(TestTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    List<TestTable> selectByExample(TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    TestTable selectByPrimaryKey(Long testId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    int updateByExampleSelective(@Param("record") TestTable record, @Param("example") TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    int updateByExample(@Param("record") TestTable record, @Param("example") TestTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    int updateByPrimaryKeySelective(TestTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_table
     *
     * @mbggenerated Sun Mar 08 20:46:21 CST 2020
     */
    int updateByPrimaryKey(TestTable record);
}