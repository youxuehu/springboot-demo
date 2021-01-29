package com.example.springbootdemo.utils;

import org.apache.hadoop.hbase.io.ByteBufferOutputStream;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RowKeyUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RowKeyUtil.class);

    /**
     * 构建row kry
     * @param jobId
     * @param seq
     * @param sunJobId
     * @param size
     * @return
     */
    public static byte[] buildRowKey(String jobId, Integer seq, Integer sunJobId, int size) {
        if (sunJobId == null) {
            sunJobId = 0;
        }
        if (seq == null) {
            seq = 0;
        }
        ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream(size);
        try {
            byteBufferOutputStream.write(Bytes.toBytes(jobId.hashCode()), 0, 4);
            byteBufferOutputStream.write(Bytes.toBytes(jobId), 0, 20);
            byteBufferOutputStream.write(Bytes.toBytes(seq), 0, 4);
            byteBufferOutputStream.write(Bytes.toBytes(sunJobId), 0, 4);
        } catch (Exception e) {
            LOGGER.error("build row key error", e);
        }
        return byteBufferOutputStream.toByteArray(0, size);
    }
}
