package com.example.springbootdemo;

import com.example.springbootdemo.common.hadoop2.HDFS2Utils;
import com.example.springbootdemo.utils.easyexcal.ExcelUtils;
import com.example.springbootdemo.utils.easyexcal.LocalWareDTO;
import com.example.springbootdemo.utils.easyexcal.LocalWareExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Slf4j
@SpringBootApplication
//@ComponentScan("com.tiger.elasticsearch")
@MapperScan(basePackages={"com.example.springbootdemo.**.mapper"})
//@Order(1)
@EnableScheduling
public class SpringbootDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);

        for (int i = 1; i <= 100; i++) {
            System.out.println(i + ",zhangsan"+i);
        }
    }

//    @Resource
//    HDFS2Utils hdfs2Utils;

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