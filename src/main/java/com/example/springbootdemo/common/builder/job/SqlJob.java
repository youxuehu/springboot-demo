package com.example.springbootdemo.common.builder.job;

public class SqlJob extends Job {
    private String sqlContent;

    public String getSqlContent() {
        return sqlContent;
    }

    public void setSqlContent(String sqlContent) {
        this.sqlContent = sqlContent;
    }
}
