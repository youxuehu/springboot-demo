package com.example.primarysofabootclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@ImportResource({"classpath*:service-bean.xml"})
public class PrimarySofaBootClientApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(PrimarySofaBootClientApplication.class, args);
		RequestMappingHandlerMapping bean = applicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
		Set<String> urls = new HashSet<>();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
			RequestMappingInfo key = entry.getKey();
			PatternsRequestCondition patternsCondition = key.getPatternsCondition();
			Set<String> patterns = patternsCondition.getPatterns();
			urls.addAll(patterns);
		}
		System.out.println(urls);
		for (String url : urls) {
			System.out.println(url);
		}
	}

}
