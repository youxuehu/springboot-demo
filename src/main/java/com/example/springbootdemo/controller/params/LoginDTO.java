package com.example.springbootdemo.controller.params;

import lombok.Data;
import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {

    private String userName;
    private String password;

}
