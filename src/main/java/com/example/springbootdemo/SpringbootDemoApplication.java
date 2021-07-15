package com.example.springbootdemo;

import com.example.common.db.service.testspring.TestSpringService;
import com.thebeastshop.forest.springboot.annotation.ForestScan;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication(exclude = {KafkaAutoConfiguration.class})
//@ComponentScan("com.tiger.elasticsearch")
@MapperScan(basePackages={"com.example.common.**.mapper"})
//@Order(1)
@EnableScheduling
@EnableCaching
@ImportResource(locations = {"classpath:applicationContext.xml"})
@ForestScan(basePackages = "com.example.springbootdemo.utils.http.api")
@ComponentScan(value = "com.example.common.db")
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