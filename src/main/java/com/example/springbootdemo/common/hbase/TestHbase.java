package com.example.springbootdemo.common.hbase;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestHbase  implements ApplicationRunner {
    @Autowired
    private HbaseTemplate hbaseTemplate;
    @Override
    public void run(ApplicationArguments args) throws Exception {
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
}
