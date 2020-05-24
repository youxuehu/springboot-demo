package com.example.springbootdemo.common.hbase;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestHbase  implements InitializingBean {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private void insertUserinfo() {
        hbaseTemplate.execute("userinfos5", new TableCallback<Object>() {
            @Override
            public Object doInTable(HTableInterface hTableInterface) throws Throwable {
                List<Put> puts = new ArrayList<>();
                for (int i = 1; i <= 1000; i++) {
                    Put put = new Put(("jack_row_key" + i).getBytes());
                    put.add("info".getBytes(), ("name").getBytes(), ("jack"+i).getBytes());
                    puts.add(put);
                }
                hTableInterface.put(puts);
                List<Put> puts2 = new ArrayList<>();
                for (int i = 1; i <= 1000; i++) {
                    Put put = new Put(("jack_row_key" + i).getBytes());
                    put.add("info".getBytes(), ("age").getBytes(), (""+i).getBytes());
                    puts2.add(put);
                }
                hTableInterface.put(puts2);
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

    @Override
    public void afterPropertiesSet() throws Exception {
        //        insertTableData();
//        insertUserinfo();
    }
}
