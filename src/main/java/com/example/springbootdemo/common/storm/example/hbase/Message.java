package com.example.springbootdemo.common.storm.example.hbase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = -8236582682706831589L;
    private String name;
    private Integer age;
    private String gender;

}
