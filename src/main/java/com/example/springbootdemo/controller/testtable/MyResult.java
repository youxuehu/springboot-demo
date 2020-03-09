package com.example.springbootdemo.controller.testtable;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @param <T>
 */
@Setter
@Getter
public class MyResult<T> implements Serializable {
    private boolean success = false;
    private String errorCode;
    private String errorMessage;
    private T data;

    public MyResult<T> appenderrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
        this.setSuccess(false);
        return this;
    }

    public MyResult<T> errorResult(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.setSuccess(false);
        return this;
    }

    public MyResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public MyResult() {
    }

    public MyResult(boolean success, String errorCode, String errorMessage) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "MyResult{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
