package com.example.springbootdemo.manager;

public interface JobManager {

    String submit(String content);

    String execute(ExecutionContext executionContext, String content);
}
