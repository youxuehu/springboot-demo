package com.example.springbootdemo.common.storm.example.hbase;

import java.io.IOException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import org.apache.hadoop.hbase.util.Bytes;

public class PrinterBolt extends BaseBasicBolt {

    public final String tableName = "storm_kafka_hbase_20200524";
    public Connection connection;
    public Configuration conf;
    public Admin admin;

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
        conf = HBaseConfiguration.create();
        conf.set("hbase.master", "master:60000");
        conf.set("hbase.zookeeper.quorum", "master:2181,slave1:2181,slave2:2181");
        try {
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();
            insertHbaseTable(tableName, tuple.getString(0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != admin) {
                try {
                    admin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void insertHbaseTable(String tableName, String data) {
        try {
            if (StringUtils.isBlank(data)) {
                return;
            }
            Message message = JSON.parseObject(data, new TypeReference<Message>() {
            });
            if (!admin.tableExists(TableName.valueOf(tableName))) {
                HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
                hTableDescriptor.addFamily(new HColumnDescriptor("message"));
                admin.createTable(hTableDescriptor);
            }
            Table table = connection.getTable(TableName.valueOf(tableName));
            String rowKey = "strom_kafka_hbase_" + System.currentTimeMillis();
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes("message"), Bytes.toBytes("name"), Bytes.toBytes(message.getName()));
            put.addColumn(Bytes.toBytes("message"), Bytes.toBytes("age"), Bytes.toBytes(message.getAge()));
            put.addColumn(Bytes.toBytes("message"), Bytes.toBytes("gender"), Bytes.toBytes(message.getGender()));
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer ofd) {
    }

}
