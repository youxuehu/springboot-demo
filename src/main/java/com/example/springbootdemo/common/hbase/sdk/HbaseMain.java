package com.example.springbootdemo.common.hbase.sdk;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class HbaseMain implements InitializingBean
{
//    @Autowired
    HbaseOperation baseOperation;

    public void main()
    {
        try {
            baseOperation.initconnection();
//            baseOperation.createTable();
            //baseOperation.insert();
            baseOperation.queryTable();
            //baseOperation.queryTableByRowKey("row1");
            //baseOperation.queryTableByCondition("Kitty");
            //baseOperation.deleteColumnFamily("columnfamily_1");
            //baseOperation.deleteByRowKey("row1");
            //baseOperation.truncateTable();
            //baseOperation.deleteTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        main();
    }
}