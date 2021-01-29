package com.example.springbootdemo.daemon;

public enum JobStatus {

    SUCCESS("success"),
    FAIL("fail"),
    SUBMITTED("submitted"),
    PREPARED("prepared"),
    RUNNINT("running"),
    ;

    JobStatus(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
