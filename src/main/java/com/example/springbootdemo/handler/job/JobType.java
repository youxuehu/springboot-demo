package com.example.springbootdemo.handler.job;

public enum JobType {
    SQL_JOB("SQL_JOB"),
    SHELL_JOB("SHELL_JOB"),
    STOP_JOB("STOP_JOB"),
    ODPS_JOB("ODPS_JOB"),
    PYTHON_JOB("PYTHON_JOB"),
    YARN_JOB("YARN_JOB"),
    KUBEMAKER_JOB("KUBEMAKER_JOB"),
    LAUNCH_CONTAINER("LAUNCH_CONTAINER"),
    ;

    JobType(String codeName) {
        this.codeName = codeName;
    }

    String codeName;

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}
