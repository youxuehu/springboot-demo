package com.example.primarysofabootclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:service-bean.xml"})
public class PrimarySofaBootClientApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(PrimarySofaBootClientApplication.class, args);
	}

}
