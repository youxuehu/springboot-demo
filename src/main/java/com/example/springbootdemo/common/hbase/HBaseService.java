package com.example.springbootdemo.common.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HBaseService implements InitializingBean {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    public void readTable(String tableName, String rowName) {
        try {
            hbaseTemplate.execute(tableName, new TableCallback<Object>() {
                @Override
                public Object doInTable(HTableInterface hTableInterface) throws Throwable {
                    Get get = new Get("r1".getBytes());
                    Result result = hTableInterface.get(get);
                    List<Cell> cells = result.listCells();
                    if (CollectionUtils.isEmpty(cells)) {
                        return null;
                    }
                    for (Cell cell : cells) {
                        byte[] value = cell.getValue();
    //                    System.out.println(Bytes.toString(value));
                        byte[] qualifier = cell.getQualifier();
                        System.out.println(Bytes.toString(qualifier));
                        long timestamp = cell.getTimestamp();
                        System.out.println(timestamp);
                    }
                    return null;
                }
            });

            hbaseTemplate.execute(tableName, new TableCallback<Object>() {
                @Override
                public Object doInTable(HTableInterface hTableInterface) throws Throwable {
                    List<Put> puts = new ArrayList<>();
                    for (int i = 0; i < 100; i++) {
                        Put put = new Put("r1".getBytes());
                        put.add("meta_data".getBytes(), ("person"+i).getBytes(), ("jack_ma"+i).getBytes());
                        puts.add(put);
                    }
    //                Put put = new Put("123".getBytes());
    //                put.add("meta_data".getBytes(), "company2".getBytes(), "ant".getBytes());
    //                Put put2 = new Put("123".getBytes());
    //                put2.add("meta_data".getBytes(), "company3".getBytes(), "mayi".getBytes());
    //                Put put3 = new Put("123".getBytes());
    //                put3.add("meta_data".getBytes(), "company4".getBytes(), "alibbaa".getBytes());
    //                List<Put> puts = new ArrayList<>();
    //                puts.add(put);
    //                puts.add(put2);
    //                puts.add(put3);
                    hTableInterface.put(puts);
    //                hTableInterface.put(put2);
    //                hTableInterface.put(put3);
                    return true;
                }
            });
            hbaseTemplate.find(tableName, new Scan(), new RowMapper<Object>() {
                @Override
                public Object mapRow(Result result, int i) throws Exception {
                    List<KeyValue> list = result.list();
                    for (KeyValue keyValue : list) {

                        byte[] key = keyValue.getKey();
                        System.out.println(new String(key));
                    }
                    return null;
                }
            });


            Object o = hbaseTemplate.get(tableName, rowName, new RowMapper<Object>() {

                @Override
                public Object mapRow(Result result, int i) throws Exception {
                    System.out.println("i = "+i);
                    List<Cell> cells = result.listCells();
                    for (Cell cell : cells) {
                        byte[] valueArray = cell.getValueArray();
    //                    System.out.println(new String(valueArray));
                        byte[] value = cell.getValue();
    //                    System.out.println("========");
                        System.out.println(new String(value));
                    }
                    return cells;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Result> getRowKeyAndColumn(String tableName, String startRowkey, String stopRowkey, String column, String qualifier) {
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        if (StringUtils.isNotBlank(column)) {
            log.debug("{}", column);
            filterList.addFilter(new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(column))));
        }
        if (StringUtils.isNotBlank(qualifier)) {
            log.debug("{}", qualifier);
            filterList.addFilter(new QualifierFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(qualifier))));
        }
        Scan scan = new Scan();
        if (filterList.getFilters().size() > 0) {
            scan.setFilter(filterList);
        }
        scan.setStartRow(Bytes.toBytes(startRowkey));
        scan.setStopRow(Bytes.toBytes(stopRowkey));

        return hbaseTemplate.find(tableName, scan, (rowMapper, rowNum) -> rowMapper);
    }

    private List<Result> getListRowkeyData(String tableName, List<String> rowKeys, String familyColumn, String column) {
        return rowKeys.stream().map(rk -> {
            if (StringUtils.isNotBlank(familyColumn)) {
                if (StringUtils.isNotBlank(column)) {
                    return hbaseTemplate.get(tableName, rk, familyColumn, column, (rowMapper, rowNum) -> rowMapper);
                } else {
                    return hbaseTemplate.get(tableName, rk, familyColumn, (rowMapper, rowNum) -> rowMapper);
                }
            }
            return hbaseTemplate.get(tableName, rk, (rowMapper, rowNum) -> rowMapper);
        }).collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Result> results = getRowKeyAndColumn("table_book", "111", "111", "meta_data", "email");
        results.forEach(rs -> {
            List<Cell> cells = rs.listCells();
            cells.forEach(cell -> {
//                System.out.println(cell);
//                byte[] familyArray = cell.getFamilyArray();
//                System.out.println(new String(familyArray));
//                byte[] family = cell.getFamily();
//                System.out.println(new String(family));
//                byte[] qualifierArray = cell.getQualifierArray();
//                System.out.println(new String(qualifierArray));
//                byte[] qualifier = cell.getQualifier();
//                System.out.println(new String(qualifier));
//                byte[] value = cell.getValue();
//                System.out.println(new String(value));
            });
        });

        readTable("table_book", "r1");
    }
}
