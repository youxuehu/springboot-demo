package com.example.springbootdemo.utils.choose;

public enum TypeEnum {

    OSS("0", "oss"),

    AFS("1", "afs")

    ;

    String code;
    String value;

    TypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

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
