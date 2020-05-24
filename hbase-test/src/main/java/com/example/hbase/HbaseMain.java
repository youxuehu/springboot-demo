package com.example.hbase;



public class HbaseMain
{
    public static void main(String[] args) throws Exception
    {
        HbaseOperation baseOperation = new HbaseOperation();
        baseOperation.initconnection();
        baseOperation.createTable();
        //baseOperation.insert();
        //baseOperation.queryTable();
        //baseOperation.queryTableByRowKey("row1");
        //baseOperation.queryTableByCondition("Kitty");
        //baseOperation.deleteColumnFamily("columnfamily_1");
        //baseOperation.deleteByRowKey("row1");
        //baseOperation.truncateTable();
        //baseOperation.deleteTable();
    }
}