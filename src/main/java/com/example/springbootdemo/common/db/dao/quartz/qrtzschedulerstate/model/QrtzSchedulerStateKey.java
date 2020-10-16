package com.example.springbootdemo.common.db.dao.quartz.qrtzschedulerstate.model;

public class QrtzSchedulerStateKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QRTZ_SCHEDULER_STATE.SCHED_NAME
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    private String schedName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QRTZ_SCHEDULER_STATE.INSTANCE_NAME
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    private String instanceName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QRTZ_SCHEDULER_STATE.SCHED_NAME
     *
     * @return the value of QRTZ_SCHEDULER_STATE.SCHED_NAME
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    public String getSchedName() {
        return schedName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QRTZ_SCHEDULER_STATE.SCHED_NAME
     *
     * @param schedName the value for QRTZ_SCHEDULER_STATE.SCHED_NAME
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    public void setSchedName(String schedName) {
        this.schedName = schedName == null ? null : schedName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QRTZ_SCHEDULER_STATE.INSTANCE_NAME
     *
     * @return the value of QRTZ_SCHEDULER_STATE.INSTANCE_NAME
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QRTZ_SCHEDULER_STATE.INSTANCE_NAME
     *
     * @param instanceName the value for QRTZ_SCHEDULER_STATE.INSTANCE_NAME
     *
     * @mbggenerated Thu Oct 08 21:39:14 CST 2020
     */
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName == null ? null : instanceName.trim();
    }
}