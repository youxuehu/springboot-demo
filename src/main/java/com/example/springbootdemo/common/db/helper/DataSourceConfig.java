package com.example.springbootdemo.common.db.helper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean("master7DataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource master7DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave71DataSource")
    @ConfigurationProperties("spring.datasource.slave71")
    public DataSource slave71DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave72DataSource")
    @ConfigurationProperties("spring.datasource.slave72")
    public DataSource slave72DataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean("targetDataSource")
    public DataSource myRoutingDataSource(@Qualifier("master7DataSource") DataSource master7DataSource,
                                          @Qualifier("slave71DataSource") DataSource slave71DataSource,
                                          @Qualifier("slave72DataSource") DataSource slave72DataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DBTypeEnum.MASTER7, master7DataSource);
        targetDataSource.put(DBTypeEnum.SLAVE71, slave71DataSource);
        targetDataSource.put(DBTypeEnum.SLAVE72, slave72DataSource);
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(master7DataSource);
        routingDataSource.setTargetDataSources(targetDataSource);
        return routingDataSource;
    }
}
