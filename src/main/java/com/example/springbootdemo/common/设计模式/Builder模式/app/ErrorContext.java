package com.example.springbootdemo.common.设计模式.Builder模式.app;

public class ErrorContext {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    private String errorCode;
    private String errorMessage;
    private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<>();

    public static ErrorContext instance() {
        ErrorContext errorContext = LOCAL.get();
        if (errorContext == null) {
            errorContext = new ErrorContext();
            LOCAL.set(errorContext);
        }
        return errorContext;
    }

    public ErrorContext errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ErrorContext errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public ErrorContext reset() {
        this.errorCode = null;
        this.errorMessage = null;
        LOCAL.remove();
        return this;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        if (this.errorCode != null) {
            message.append(LINE_SEPARATOR);
            message.append("#### ");
            message.append(this.errorCode);
        }
        if (this.errorMessage != null) {
            message.append(LINE_SEPARATOR);
            message.append("#### ");
            message.append(this.errorMessage);
        }
        return message.toString();
    }
}
