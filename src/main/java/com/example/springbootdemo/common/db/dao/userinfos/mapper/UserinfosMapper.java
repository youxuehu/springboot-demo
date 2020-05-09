package com.example.springbootdemo.common.db.dao.userinfos.mapper;

import com.example.springbootdemo.common.db.dao.userinfos.model.Userinfos;
import com.example.springbootdemo.common.db.dao.userinfos.model.UserinfosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserinfosMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    int countByExample(UserinfosExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    int deleteByExample(UserinfosExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    int insert(Userinfos record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    int insertSelective(Userinfos record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    List<Userinfos> selectByExample(UserinfosExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    Userinfos selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    int updateByExampleSelective(@Param("record") Userinfos record, @Param("example") UserinfosExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    int updateByExample(@Param("record") Userinfos record, @Param("example") UserinfosExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    int updateByPrimaryKeySelective(Userinfos record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userinfos
     *
     * @mbggenerated Sat Apr 11 22:34:22 CST 2020
     */
    int updateByPrimaryKey(Userinfos record);
}