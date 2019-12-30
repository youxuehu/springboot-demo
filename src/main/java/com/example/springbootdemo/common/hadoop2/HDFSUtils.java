package com.example.springbootdemo.common.hadoop2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
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

    public void readFile(FileSystem fs) throws Exception {
        FSDataInputStream inputStream = fs.open(new Path("/tiger/node/1.txt"));
        List<String> readLines = IOUtils.readLines(inputStream, "UTF-8");
        for (String line : readLines) {
            log.info("{}", line);
        }
        log.info("{}", readLines.size());
    }

    public void uploadFIle(FileSystem fs) throws Exception {
        fs.copyFromLocalFile(new Path("/Users/youxuehu/IdeaProjects/springboot-demo/src/main/java/com/example/springbootdemo/common/hadoop2/HDFSUtils.java"), new Path("/tiger/node"));
        log.info("{}", "upload file success");
    }

    public void fileList(FileSystem fs) throws Exception {
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            log.info("{}", fileStatus);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        mkdir();
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfsUrl);
        conf.setBoolean("dfs.support.append", true);
        System.setProperty("HADOOP_USER_NAME", "root");
        FileSystem fs = FileSystem.get(conf);
        readFile(fs);
        uploadFIle(fs);
        fileList(fs);
    }
}
