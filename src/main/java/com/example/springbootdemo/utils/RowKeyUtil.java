package com.example.springbootdemo.utils;

import org.apache.hadoop.hbase.util.Bytes;

public class RowKeyUtil {

    public static String getRowKey(String jobId) {
        byte[] bytes = Bytes.toBytes(jobId.hashCode());
        return null;
    }
}
