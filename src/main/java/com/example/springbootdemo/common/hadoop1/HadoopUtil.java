package com.example.springbootdemo.common.hadoop1;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hadoop1.2.1 客户端连接
 */
//@Component
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

    public boolean createFile(String path) throws Exception {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
            boolean newFile = fileSystem.createNewFile(new Path(path));
            return newFile;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public void uploadFile(String srcPath, String targetFile) throws Exception {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
            fileSystem.copyFromLocalFile(new Path(srcPath), new Path(targetFile));
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

    public List<String> readFile(String filePath) throws Exception {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
            FSDataInputStream inputStream = fileSystem.open(new Path(filePath));
            List<String> lines = IOUtils.readLines(inputStream);
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public void downloadFile(String srcPath, String targetPath) throws Exception {
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
            fileSystem.copyToLocalFile(new Path(srcPath), new Path(targetPath));
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


    public Map<String, Object> fileStatus(String path) throws Exception {
        Map<String, Object> map  = new HashMap<>();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(configuration);
            FileStatus fileStatus = fileSystem.getFileStatus(new Path(path));
            String owner = fileStatus.getOwner();
            Path path1 = fileStatus.getPath();
            FsPermission permission = fileStatus.getPermission();
            permission.toString();
            map.put("owner", owner);
            map.put("path1", path1);
            map.put("permission", permission);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return map;
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
