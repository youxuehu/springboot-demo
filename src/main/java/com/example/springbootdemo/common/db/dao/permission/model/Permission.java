package com.example.springbootdemo.common.db.dao.permission.model;

public class Permission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.id
     *
     * @mbggenerated Sat Jan 16 23:55:48 CST 2021
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column permission.userNumber
     *
     * @mbggenerated Sat Jan 16 23:55:48 CST 2021
     */
    private String usernumber;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.id
     *
     * @return the value of permission.id
     *
     * @mbggenerated Sat Jan 16 23:55:48 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.id
     *
     * @param id the value for permission.id
     *
     * @mbggenerated Sat Jan 16 23:55:48 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column permission.userNumber
     *
     * @return the value of permission.userNumber
     *
     * @mbggenerated Sat Jan 16 23:55:48 CST 2021
     */
    public String getUsernumber() {
        return usernumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column permission.userNumber
     *
     * @param usernumber the value for permission.userNumber
     *
     * @mbggenerated Sat Jan 16 23:55:48 CST 2021
     */
    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber == null ? null : usernumber.trim();
    }
}