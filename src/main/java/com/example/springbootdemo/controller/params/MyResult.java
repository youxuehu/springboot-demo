package com.example.springbootdemo.controller.params;

import java.io.Serializable;

/**
 * @param <T>
 */
public class MyResult<T> implements Serializable {
    private boolean success = false;
    private String errorCode;
    private String errorMessage;
    private T data;
    private Integer total;
    private Integer pageIndex;
    private Integer pageSize;

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

    public MyResult<T> success(T data) {
        this.success = true;
        this.setData(data);
        return this;
    }

    public MyResult<T> success() {
        this.success = true;
        return this;
    }

    public MyResult<T> error(String errorCode, String errorMessage) {
        this.success = false;
        this.setErrorMessage(errorMessage);
        this.setErrorCode(errorCode);
        return this;
    }

    public MyResult() {
    }

    public MyResult(boolean success, String errorCode, String errorMessage) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "MyResult{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
