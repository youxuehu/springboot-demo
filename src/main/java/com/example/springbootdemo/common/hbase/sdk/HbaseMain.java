package com.example.springbootdemo.common.hbase.sdk;

public class HbaseMain {

    public static void main(String[] args) {
        try {
            HbaseOperation baseOperation = new HbaseOperation();
            baseOperation.initconnection();
//            baseOperation.createTable();
//            baseOperation.insert();
//            baseOperation.queryTable("tableName");
//            baseOperation.queryTableByRowKey("row1");
//            baseOperation.queryTableByCondition("Kitty");
//            baseOperation.deleteColumnFamily("columnfamily_1");
//            baseOperation.deleteByRowKey("row1");
//            baseOperation.truncateTable();
//            baseOperation.deleteTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}