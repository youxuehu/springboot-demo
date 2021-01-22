package com.example.springbootdemo.common.db.dao.executionlog.model;

import java.util.Date;

public class ExecutionLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execution_log.id
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execution_log.gmt_create
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execution_log.job_id
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    private String jobId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execution_log.content
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execution_log.id
     *
     * @return the value of execution_log.id
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execution_log.id
     *
     * @param id the value for execution_log.id
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execution_log.gmt_create
     *
     * @return the value of execution_log.gmt_create
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execution_log.gmt_create
     *
     * @param gmtCreate the value for execution_log.gmt_create
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execution_log.job_id
     *
     * @return the value of execution_log.job_id
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execution_log.job_id
     *
     * @param jobId the value for execution_log.job_id
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execution_log.content
     *
     * @return the value of execution_log.content
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execution_log.content
     *
     * @param content the value for execution_log.content
     *
     * @mbggenerated Fri Jan 22 23:05:14 CST 2021
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}