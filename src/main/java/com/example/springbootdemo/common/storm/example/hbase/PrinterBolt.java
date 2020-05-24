package com.example.springbootdemo.common.storm.example.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import org.apache.hadoop.hbase.util.Bytes;

public class PrinterBolt extends BaseBasicBolt {

    public static final String TableName = "table_book";
    public static Connection connection;
    public static Configuration conf;
    public static Admin admin;

    public static void selectRowKey(String tablename, String rowKey) throws IOException {
        connection = ConnectionFactory.createConnection(conf);
        admin = connection.getAdmin();
        insert();
    }

    private static void insert() throws IOException {
        System.out.println("[hbaseoperation] start insert...");
        Table table = connection.getTable(org.apache.hadoop.hbase.TableName.valueOf("table_book"));
        List<Put> putList = new ArrayList<Put>();
        Put put1;
        put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("columnfamily_1"), Bytes.toBytes("name"), Bytes.toBytes("<<Java In Action>>"));
        put1.addColumn(Bytes.toBytes("columnfamily_1"), Bytes.toBytes("price"), Bytes.toBytes("98.50"));
        put1.addColumn(Bytes.toBytes("columnfamily_2"), Bytes.toBytes("author"), Bytes.toBytes("Tom"));
        put1.addColumn(Bytes.toBytes("columnfamily_2"), Bytes.toBytes("version"), Bytes.toBytes("3 thrd"));
        put1.addColumn(Bytes.toBytes("columnfamily_3"), Bytes.toBytes("discount"), Bytes.toBytes("5%"));

        Put put2;
        put2 = new Put(Bytes.toBytes("row2"));
        put2.addColumn(Bytes.toBytes("columnfamily_1"), Bytes.toBytes("name"), Bytes.toBytes("<<C++ Prime>>"));
        put2.addColumn(Bytes.toBytes("columnfamily_1"), Bytes.toBytes("price"), Bytes.toBytes("68.88"));
        put2.addColumn(Bytes.toBytes("columnfamily_2"), Bytes.toBytes("author"), Bytes.toBytes("Jimmy"));
        put2.addColumn(Bytes.toBytes("columnfamily_2"), Bytes.toBytes("version"), Bytes.toBytes("5 thrd"));
        put2.addColumn(Bytes.toBytes("columnfamily_3"), Bytes.toBytes("discount"), Bytes.toBytes("15%"));

        Put put3;
        put3 = new Put(Bytes.toBytes("row3"));
        put3.addColumn(Bytes.toBytes("columnfamily_1"), Bytes.toBytes("name"), Bytes.toBytes("<<Hadoop in Action>>"));
        put3.addColumn(Bytes.toBytes("columnfamily_1"), Bytes.toBytes("price"), Bytes.toBytes("78.92"));
        put3.addColumn(Bytes.toBytes("columnfamily_2"), Bytes.toBytes("author"), Bytes.toBytes("Kitty"));
        put3.addColumn(Bytes.toBytes("columnfamily_2"), Bytes.toBytes("version"), Bytes.toBytes("2 thrd"));
        put3.addColumn(Bytes.toBytes("columnfamily_3"), Bytes.toBytes("discount"), Bytes.toBytes("20%"));
        putList.add(put1);
        putList.add(put2);
        putList.add(put3);
        table.put(putList);
        System.out.println("[hbaseoperation] start insert...");
    }

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
        System.out.println(tuple.getString(0));
        conf = HBaseConfiguration.create();
        conf.set("hbase.master", "master:60000");
        conf.set("hbase.zookeeper.quorum", "master:2181,slave1:2181,slave2:2181");
        try {
            System.out.println("[1]=============");
            selectRowKey(TableName, tuple.getString(0));
            System.out.println("[2]=============");
        } catch (Exception e) {
            System.out.println("[3]=============");
            System.out.println(tuple);
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer ofd) {
    }

}
