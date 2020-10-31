package com.example.springbootdemo.controller.hadoop.param;

import org.springframework.web.multipart.MultipartFile;

public class TaskVO {
    private MultipartFile jarPath;
    private String className;
    private String inputPath;
    private String outputPath;

    public MultipartFile getJarPath() {
        return jarPath;
    }

    public void setJarPath(MultipartFile jarPath) {
        this.jarPath = jarPath;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
