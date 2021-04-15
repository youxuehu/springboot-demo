package com.example.springbootdemo.common.反射包;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterNamesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

/**
 *         <dependency>
 *             <groupId>org.reflections</groupId>
 *             <artifactId>reflections</artifactId>
 *             <version>0.9.11</version>
 *         </dependency>
 */
public class FansheMain {

    public static void main(String[] args) {

        Reflections reflections = new Reflections("com.example.*",
                Arrays.asList(new SubTypesScanner(false),
                        new MethodParameterNamesScanner(),
                        new MethodAnnotationsScanner(),
                        new TypeAnnotationsScanner()));
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        classes.stream().forEach(clazz -> {
            System.out.println(clazz);
        });

    }
}
