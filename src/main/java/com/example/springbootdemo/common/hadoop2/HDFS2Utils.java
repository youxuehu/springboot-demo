package com.example.springbootdemo.common.hadoop2;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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

    /**
     * 列出hdfs上所有的文件，打印出文件的全路径
     * @param path
     * @param files
     * @throws Exception
     */
    public void fetchFiles(String path, List<FileInfo> files) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        FileStatus[] fileStatuses = fs.listStatus(new Path(path));
        for (FileStatus status : fileStatuses) {
//            System.out.println(status.getPath().toString());
            FileInfo fileInfo = new FileInfo();
            fileInfo.setName(status.getOwner());
            fileInfo.setPath(status.getPath().toString());
            files.add(fileInfo);
            if (fs.isDirectory(new Path(status.getPath().toString()))) {
                fetchFiles(status.getPath().toString(), files);
            }
        }
    }

    @Data@Setter@Getter
    public static class FileInfo {
        private String name;
        private String path;

    }

    /**
     * 列出hdfs上所有的文件夹目录
     * @param path
     * @param dirs
     * @throws Exception
     */
    public void fetchFileDirs(String path, List<String> dirs) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        FileStatus[] fileStatuses = fs.listStatus(new Path(path));
        for (FileStatus status : fileStatuses) {
            if (fs.isDirectory(new Path(status.getPath().toString()))) {
                System.out.println(status.getPath().toString());
                dirs.add(status.getPath().toString());
                fetchFileDirs(status.getPath().toString(), dirs);
            }
        }
    }

    public boolean isExistsFile(String path) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        return fs.exists(new Path(path));
    }

    /**
     * 删除一个文件
     * @param hdfsPath
     * @throws Exception
     */
    public void delete(String hdfsPath) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        // 递归删除
        fs.delete(new Path(hdfsPath), true);
    }

    /**
     * 删除整个目录
     * @param hdfsPath
     * @throws Exception
     */
    public void deleteWithDir(String hdfsPath) throws Exception {
        FileSystem fs = FileSystem.get(configuration);
        if (fs.isFile(new Path(hdfsPath))) {
            fs.delete(new Path(hdfsPath));
            return;
        }
        List<FileInfo> fileInfos = new ArrayList<>();
        if (fs.isDirectory(new Path(hdfsPath))) {
            fetchFiles(hdfsPath, fileInfos);
        }
        for (FileInfo fileInfo : fileInfos) {
            fs.delete(new Path(fileInfo.getPath()));
        }
        List<String> dirs = new ArrayList<>();
        fetchFileDirs(hdfsPath, dirs);
        for (String dirPath : dirs) {
            fs.delete(new Path(dirPath));
        }
    }
}
