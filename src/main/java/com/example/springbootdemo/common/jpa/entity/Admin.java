//package com.example.springbootdemo.common.jpa.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
//@Entity
//@Getter
//@Setter
//public class Admin {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String userName;
//
//    @Column(nullable = false)
//    private String passWord;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    @Column(nullable = true, unique = true)
//    private String nickName;
//
//    @Column(nullable = false)
//    private String regTime;
//
//    public Admin(String userName, String passWord, String email, String nickName, String regTime) {
//        this.userName = userName;
//        this.passWord = passWord;
//        this.email = email;
//        this.nickName = nickName;
//        this.regTime = regTime;
//    }
//
//    public Admin() {
//    }
//
//}