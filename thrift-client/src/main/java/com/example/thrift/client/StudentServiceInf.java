package com.example.thrift.client;

import com.example.thrift.thriftcode.Student;

public interface StudentServiceInf {

    //根据名称获取学生信息
    Student getStudentByName(String name);

    //保存学生信息
    void save(Student student);

}
