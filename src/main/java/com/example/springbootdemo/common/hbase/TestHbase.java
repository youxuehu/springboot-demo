package com.example.springbootdemo.common.hbase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TestHbase {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private void insertUserinfo() {
        hbaseTemplate.execute("userinfos", new TableCallback<Object>() {
            @Override
            public Object doInTable(HTableInterface hTableInterface) throws Throwable {
                List<Put> puts = new ArrayList<>();
                for (int i = 1; i <= 1000; i++) {
                    Put put = new Put(("jack_row_key" + i).getBytes());
                    put.add("info".getBytes(), ("name").getBytes(), ("jack"+i).getBytes());
                    put.add("info".getBytes(), ("age").getBytes(), (""+i).getBytes());
                    puts.add(put);
                }
                log.info("{}", JSON.toJSONString(puts, SerializerFeature.PrettyFormat));
                hTableInterface.put(puts);
                return true;
            }
        });
    }

    public void insertTableData() {
            hbaseTemplate.execute("hbase_emp_table", new TableCallback<Object>() {
            @Override
            public Object doInTable(HTableInterface hTableInterface) throws Throwable {
                // ":key,info:ename,info:job,info:mgr,info:hiredate,info:sal,info:comm,info:deptno")
                List<Put> puts = new ArrayList<>();
                for (int i = 1; i <= 100; i++) {
                    Put put = new Put(("emp_row" + i).getBytes());
                    put.add("info".getBytes(), ("ename").getBytes(), ("tige"+i).getBytes());
                    puts.add(put);
                }
                hTableInterface.put(puts);
                List<Put> puts2 = new ArrayList<>();
                for (int i = 1; i <= 100; i++) {
                    Put put = new Put(("emp_row" + i).getBytes());
                    put.add("info".getBytes(), ("job").getBytes(), ("pragrammer"+i).getBytes());
                    puts2.add(put);
                }
                hTableInterface.put(puts2);
                List<Put> puts3 = new ArrayList<>();
                for (int i = 1; i <= 100; i++) {
                    Put put = new Put(("emp_row" + i).getBytes());
                    put.add("info".getBytes(), ("mgr").getBytes(), (""+i).getBytes());
                    puts3.add(put);
                }
                hTableInterface.put(puts3);
                return true;
            }
        });
    }
}
