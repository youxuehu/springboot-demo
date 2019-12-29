package com.example.springbootdemo.common.db.helper;

public enum DBTypeEnum {

    MASTER7("0", "master7"),

    SLAVE71("1", "slave71"),

    SLAVE72("2", "slave72"),

    ;

    DBTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    String code;
    String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
