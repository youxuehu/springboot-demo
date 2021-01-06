package com.example.springbootdemo;

import com.example.springbootdemo.common.hadoop2.HDFS2Utils;
import com.example.springbootdemo.common.thrift.impl.StudentThriftServer;
import com.example.springbootdemo.intercepter.AdminIntercepter;
import com.example.springbootdemo.service.TestSpringService;
import com.example.springbootdemo.utils.easyexcal.ExcelUtils;
import com.example.springbootdemo.utils.easyexcal.LocalWareDTO;
import com.example.springbootdemo.utils.easyexcal.LocalWareExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Slf4j
@SpringBootApplication(exclude = {KafkaAutoConfiguration.class})
//@ComponentScan("com.tiger.elasticsearch")
@MapperScan(basePackages={"com.example.springbootdemo.**.mapper"})
//@Order(1)
@EnableScheduling
@EnableCaching
@ImportResource(locations = {"classpath:applicationContext.xml"})
public class SpringbootDemoApplication implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootDemoApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootDemoApplication.class, args);
//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        TestSpringService testSpringService = (TestSpringService) applicationContext.getBean("testSpringServiceImpl");
        String message = testSpringService.sayHello(applicationContext.getApplicationName());
        LOGGER.info(message);
    }

//    @Resource
//    HDFS2Utils hdfs2Utils;
//    zhangsan	math:90,english:60
//    lisi	chinese:80,math:66,english:77
//    wangwu  chinese:66,math:55,english:80
    static String ss = "zhangsan\tmath:90,english:60\nlisi\tchinese:80,math:66,english:77\nwangwu\tchinese:66,math:55,english:80";

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        InputStream inputStream = null;
//        File file = null;
//        try {
//            file = new File("/Users/youxuehu/Desktop/export.xlsx");
//            hdfs2Utils.uploadFIle(file.getAbsolutePath(), "/");
//            inputStream = new FileInputStream(file);
//            LocalWareExcelListener<LocalWareDTO> localWareExcelListener = new LocalWareExcelListener<>();
//            Boolean flag = ExcelUtils.readExcel(inputStream, LocalWareDTO.class,1,localWareExcelListener);
//            List<LocalWareDTO> datas = localWareExcelListener.getDatas();
//            if(flag){
////                log.info("{}", datas);
//                log.info("解析数据行数为:{}",datas.size());
//            }
////            hdfs2Utils.uploadFIle(inputStream, file.getName());
//        } catch (Exception e) {
//            log.info("{}", e);
//        }

    }
}