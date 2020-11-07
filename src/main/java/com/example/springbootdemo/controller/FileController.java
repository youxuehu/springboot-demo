package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.hadoop2.HDFS2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/file")
public class FileController {

    // 文件保存路径  主要需要自己先建立对应upload文件夹路径
    private final static String FILE_UPLOAD_PATH = "/tmp/";

    @Autowired
    private HDFS2Utils hdfs2Utils;

    @RequestMapping("/uploadPage")
    public String uploadPage() {
        return "uploadPage.html";
    }

    @RequestMapping("/uploadToHdfs")
    @ResponseBody
    public Map<String, String> uploadToHdfs(HttpServletRequest request, MultipartFile file) {
        Map<String, String> res = new HashMap<>();
        if (file.isEmpty()) {
            res.put("success","false");
            res.put("data", "上传失败");
            return res;
        }
        String fileName = file.getOriginalFilename();
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        //生成文件名称通用方法
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//        Random r = new Random();
//        StringBuilder tempName = new StringBuilder();
//        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
//        String newFileName = tempName.toString();
        // 保存文件
//        String hdfsPath = FILE_UPLOAD_PATH + fileName;
//        Path path = Paths.get(hdfsPath);
        try {
//            byte[] bytes = file.getBytes();
//            hdfs2Utils.delete(hdfsPath);
//            Files.write(path, bytes);
            hdfs2Utils.uploadFIle(file.getInputStream(), fileName);
            res.put("success","true");
            res.put("data", "上传成功");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success","false");
            res.put("data", "上传失败");
            return res;
        } finally {
//            try {
//                Files.deleteIfExists(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    @RequestMapping("/listFile")
    @ResponseBody
    public List<String> fetchFileDirs() {
        try {
            List<String> list = new ArrayList<>();
//            hdfs2Utils.fetchFiles("/", list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/listDir")
    @ResponseBody
    public List<HDFS2Utils.FileInfo> listDir(@RequestParam(value = "path", required = true) String path) {
        try {
            List<HDFS2Utils.FileInfo> list = new ArrayList<>();
            hdfs2Utils.fetchFiles(path, list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
