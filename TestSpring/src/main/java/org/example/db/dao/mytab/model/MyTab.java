package org.example.db.dao.mytab.model;

public class MyTab {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_tab.id
     *
     * @mbggenerated Tue Sep 22 20:58:08 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_tab.test1
     *
     * @mbggenerated Tue Sep 22 20:58:08 CST 2020
     */
    private String test1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_tab.id
     *
     * @return the value of my_tab.id
     *
     * @mbggenerated Tue Sep 22 20:58:08 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_tab.id
     *
     * @param id the value for my_tab.id
     *
     * @mbggenerated Tue Sep 22 20:58:08 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_tab.test1
     *
     * @return the value of my_tab.test1
     *
     * @mbggenerated Tue Sep 22 20:58:08 CST 2020
     */
    public String getTest1() {
        return test1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_tab.test1
     *
     * @param test1 the value for my_tab.test1
     *
     * @mbggenerated Tue Sep 22 20:58:08 CST 2020
     */
    public void setTest1(String test1) {
        this.test1 = test1 == null ? null : test1.trim();
    }
}