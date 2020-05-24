package com.example.springbootdemo.common.thrift.client;

import com.example.springbootdemo.common.thrift.shriftcode.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceInfImpl implements StudentServiceInf {
    @Autowired
    ThriftClient thriftClient;

    @Override
    public Student getStudentByName(String name) {
        try {
            thriftClient.open();
            System.out.println("客户端请求用户名为:" + name + "的数据");
            Student student = thriftClient.getService().getStudentByName(name);
            System.out.println("获取成功！！！服务端返回的对象:" + student);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            thriftClient.close();
        }
        return null;
    }

    @Override
    public void save(Student student) {
        try {
            thriftClient.open();
            System.out.println("客户端请求保存对象:" + student);
            thriftClient.getService().save(student);
            System.out.println("保存成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            thriftClient.close();
        }
    }
}

