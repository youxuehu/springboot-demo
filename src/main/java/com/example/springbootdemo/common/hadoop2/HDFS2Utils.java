package com.example.springbootdemo.common.hadoop2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
public class HDFS2Utils implements ApplicationRunner {

    @Value("${hdfs.master}")
    private String hdfsMaster;

    Configuration configuration = null;

    public void mkdir() throws Exception {

        FileSystem fs = FileSystem.get(configuration);
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

    public void readFile(String path) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        FSDataInputStream inputStream = fs.open(new Path(path));
        List<String> readLines = IOUtils.readLines(inputStream, "UTF-8");
        for (String line : readLines) {
            log.info("{}", line);
        }
        log.info("{}", readLines.size());
    }

    public void uploadFIle(String localPath, String hdfsPath) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        fs.copyFromLocalFile(new Path(localPath), new Path(hdfsPath));
        log.info("{}", "upload file success");
    }

    public void uploadFIle(InputStream inputStream, String fileName) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        FSDataOutputStream outputStream = fs.create(new Path("/" + fileName));
        IOUtils.copyLarge(inputStream, outputStream, new byte[2048]);
        log.info("{}", "upload file success");
    }

    public void fileList(String path) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(path), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            log.info("{}", fileStatus);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        mkdir();
//        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS", hdfsUrl);
//        conf.setBoolean("dfs.support.append", true);
//        System.setProperty("HADOOP_USER_NAME", "root");
//        FileSystem fs = FileSystem.get(conf);
//        readFile(fs);
//        uploadFIle(fs);
//        fileList(fs);
        configuration = new Configuration();
        configuration.set("fs.defaultFS", hdfsMaster);
        configuration.setBoolean("dfs.support.append", true);
        System.setProperty("HADOOP_USER_NAME", "root");
    }
}
