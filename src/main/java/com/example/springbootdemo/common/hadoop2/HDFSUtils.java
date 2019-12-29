package com.example.springbootdemo.common.hadoop2;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HDFSUtils implements ApplicationRunner {
    @Value("${hdfs.master}")
    String hdfsUrl;

    public void mkdir() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfsUrl);
        conf.setBoolean("dfs.support.append", true);
        System.setProperty("HADOOP_USER_NAME", "root");
        FileSystem fs = FileSystem.get(conf);
        if (!fs.exists(new Path("/tiger/node"))) {
            boolean mkdirs = fs.mkdirs(new Path("/tiger/node"));
        }
        if (!fs.exists(new Path("/tiger/node/1.txt"))) {
            fs.createNewFile(new Path("/tiger/node/1.txt"));
        }
        FSDataOutputStream fsos = fs.append(new Path("/tiger/node/1.txt"), 1024);
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < 100; i++) {
            builder.append("an idea youth , a needly age.\n");
        }
        fsos.writeUTF(builder.toString());
        fsos.flush();
        FSDataInputStream fsis = fs.open(new Path("/tiger/node/1.txt"));
        List<String> readLines = IOUtils.readLines(fsis, "utf-8");
        for (String line : readLines) {
            System.out.println(line);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        mkdir();
    }
}
