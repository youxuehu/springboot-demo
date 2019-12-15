package com.example.springbootdemo.common.hadoop1_2_1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * hadoop1.2.1 客户端连接
 */
@Component
public class HadoopUtil implements InitializingBean {

    @Value("${hdfs.master}")
    private String hdfsMaster;

    Configuration configuration = null;

    public void mkdir(String path) {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
            fileSystem.mkdirs(new Path(path));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fileSystem) {
                try {
                    fileSystem.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void delete(String path) throws Exception {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
            fileSystem.deleteOnExit(new Path(path));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fileSystem) {
                try {
                    fileSystem.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        configuration = new Configuration();
        configuration.set("fs.defaultFS", hdfsMaster);
    }
}
