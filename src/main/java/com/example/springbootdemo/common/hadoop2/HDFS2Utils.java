package com.example.springbootdemo.common.hadoop2;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Component
@Slf4j
@Order(0)
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
        InputStream inputStream = fs.open(new Path("/tiger/node/1.txt"));
        List<String> readLines = org.apache.commons.io.IOUtils.readLines(inputStream, "utf-8");
        for (String line : readLines) {
            System.out.println(line);
        }
        /**
         * 使用流的方式下载文件
         */
        //读取HDFS上的文件
        //在本地创建一个文件，返回输出流
        OutputStream outputStream = new FileOutputStream("/Users/youxuehu/IdeaProjects/springboot-demo/src/main/java/com/example/springbootdemo/common/hadoop2/word.txt");
        IOUtils.copyBytes(inputStream, outputStream, 4096, true);

    }

    public void readFile(String path) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        FSDataInputStream inputStream = fs.open(new Path(path));
        List<String> readLines = org.apache.commons.io.IOUtils.readLines(inputStream, "UTF-8");
        IOUtils.readFully(inputStream, new byte[2048], 0, 0);
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
        OutputStream outputStream = fs.create(new Path("/" + fileName));
        IOUtils.copyBytes(inputStream, outputStream, 4096, false);
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
