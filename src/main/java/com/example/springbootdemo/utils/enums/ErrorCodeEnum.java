package com.example.springbootdemo.utils.enums;

public enum ErrorCodeEnum {
    WELCOME("WELCOME", "欢迎"),
    USER_NOT_FOUND_WITH_BLOOM_FILTER("USER_NOT_FOUND_WITH_BLOOM_FILTER", "用户不存在(布隆过滤器)"),
    USER_NOT_FOUND("USER_NOT_FOUND", "用户不存在"),
    USER_PASSWORD_IS_NULL("USER_PASSWORD_IS_NULL", "密码不能是空"),
    USER_NAME_IS_NULL("USER_NAME_IS_NULL", "userName是空"),
    USER_OR_PASSWORD_ERROR("USER_OR_PASSWORD_ERROR", "用户名或密码不正确");

    String code;
    String description;

    ErrorCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
