package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
// HttpMessageConverter json序例化
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class User {

    // ID
    @Id
    //主键自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
