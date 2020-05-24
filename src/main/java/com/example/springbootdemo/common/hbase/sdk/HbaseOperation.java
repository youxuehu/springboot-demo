package com.example.springbootdemo.common.hbase.sdk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseOperation {

    public Connection connection;

    public Admin admin;

    /**
     * 初始化连接
     * @throws Exception
     */
    public void initconnection() throws Exception {
        File workaround = new File(".");
        System.getProperties().put("hadoop.home.dir", workaround.getAbsolutePath());
        // window
//        new File("./bin").mkdirs();
//        try {
//            new File("./bin/winutils.exe").createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "master,slave1,slave2");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        connection = ConnectionFactory.createConnection(conf);
        admin = connection.getAdmin();
    }

    public void createTable(String tableName, String... families) throws IOException {
        System.out.println("[hbaseoperation] start createtable...");
        TableName tName = TableName.valueOf(tableName);
        if (admin.tableExists(tName)) {
            System.out.println("[INFO] table exist");
        } else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for (String family : families) {
                hTableDescriptor.addFamily(new HColumnDescriptor(family));
            }
            admin.createTable(hTableDescriptor);
        }
        System.out.println("[hbaseoperation] end createtable...");
    }

    public void insert(String tableName) throws IOException {
        System.out.println("[hbaseoperation] start insert...");
        Table table = connection.getTable(TableName.valueOf(tableName));
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

    public void queryTable(String tableName) throws IOException {
        System.out.println("[hbaseoperation] start queryTable...");
        Table table = connection.getTable(TableName.valueOf(tableName));
        ResultScanner scanner = table.getScanner(new Scan());
        for (Result result : scanner) {
            byte[] row = result.getRow();
            System.out.println("row key is:" + Bytes.toString(row));
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                System.out.print("family:" + Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()));
                System.out.print("qualifier:" + Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()));
                System.out.print("value:" + Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                System.out.println("Timestamp:" + cell.getTimestamp());
            }
        }
        System.out.println("[hbaseoperation] end queryTable...");
    }

    public void queryTableByRowKey(String tableName, String rowkey) throws IOException {
        System.out.println("[hbaseoperation] start queryTableByRowKey...");
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowkey.getBytes());
        Result result = table.get(get);
        List<Cell> listCells = result.listCells();
        for (Cell cell : listCells) {
            String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
            long timestamp = cell.getTimestamp();
            String family = Bytes.toString(CellUtil.cloneFamily(cell));
            String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            System.out.println(" ===> rowKey : " + rowKey + ",  timestamp : " + timestamp + ", family : " + family + ", qualifier : " + qualifier + ", value : " + value);
        }
        System.out.println("[hbaseoperation] end queryTableByRowKey...");
    }


    public void queryTableByCondition(String tableName, String authorName, String family, String qualifier) throws IOException {
        System.out.println("[hbaseoperation] start queryTableByCondition...");
        Table table = connection.getTable(TableName.valueOf(tableName));
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes(family),
                Bytes.toBytes(qualifier), CompareOp.EQUAL, Bytes.toBytes(authorName));
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
                long timestamp = cell.getTimestamp();
                String familyName = Bytes.toString(CellUtil.cloneFamily(cell));
                String qualifierName = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println(" ===> rowKey : " + rowKey + ",  timestamp : " + timestamp +
                        ", family : " + familyName + ", qualifier : " + qualifierName + ", value : " + value);
            }
        }
        System.out.println("[hbaseoperation] end queryTableByCondition...");
    }

    public void deleteColumnFamily(String tableName, String cf) throws IOException {
        TableName tName = TableName.valueOf(tableName);
        admin.deleteColumn(tName, Bytes.toBytes(cf));
    }

    public void deleteByRowKey(String tableName, String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        queryTable(tableName);
    }

    public void truncateTable(String tableName) throws IOException {
        TableName tName = TableName.valueOf(tableName);
        admin.disableTable(tName);
        admin.truncateTable(tName, true);
    }

    public void deleteTable(String tableName) throws IOException {
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
    }
}
