package com.example.primarysofabootclient;

import org.example.service.HelloSyncService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:service-bean.xml"})
public class PrimarySofaBootClientApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(PrimarySofaBootClientApplication.class, args);
		HelloSyncService helloSyncService = (HelloSyncService) applicationContext.getBean("helloSyncService");
		System.out.println(helloSyncService.sayHello("jack"));
	}

}
