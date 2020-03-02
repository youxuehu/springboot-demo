package com.example.springbootdemo.common.controller;

import com.example.springbootdemo.common.hadoop2.HDFS2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/file")
public class FileController {

    // 文件保存路径  主要需要自己先建立对应upload文件夹路径
    private final static String FILE_UPLOAD_PATH = "/Users/youxuehu/SDK/upload/";

    @Autowired
    private HDFS2Utils hdfs2Utils;

    @RequestMapping("/uploadPage")
    public String uploadPage() {
        return "uploadPage.html";
    }

    @RequestMapping("/uploadToHdfs")
    @ResponseBody
    public String uploadToHdfs(HttpServletRequest request, MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败";
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        try {
            // 保存文件
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILE_UPLOAD_PATH + newFileName);
            Files.write(path, bytes);
            hdfs2Utils.uploadFIle(path.toAbsolutePath().toString(), "/");
            Files.deleteIfExists(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传成功";
    }
}
