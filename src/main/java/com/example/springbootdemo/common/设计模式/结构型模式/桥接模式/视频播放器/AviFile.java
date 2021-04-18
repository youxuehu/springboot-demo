package com.example.springbootdemo.common.设计模式.结构型模式.桥接模式.视频播放器;

/**
 * @author youxuehu
 * @version v1.0
 * @className AviFile
 * @date 2021/4/18 8:27 下午
 * @desrription avi视频文件（具体的实现化角色）
 */
public class AviFile implements VideoFile {

    @Override
    public void decode(String fileName) {
        System.out.println("avi file ：" + fileName);
    }
}
