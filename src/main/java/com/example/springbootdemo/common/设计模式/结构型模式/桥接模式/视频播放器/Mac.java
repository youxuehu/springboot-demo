package com.example.springbootdemo.common.设计模式.结构型模式.桥接模式.视频播放器;

/**
 * @author youxuehu
 * @version v1.0
 * @className Windows
 * @date 2021/4/18 8:30 下午
 * @desrription mac操作系统 (扩展抽象化角色)
 */
public class Mac extends OperationSystem {

    public Mac(VideoFile videoFile) {
        super(videoFile);
    }

    @Override
    public void play(String fileName) {
        videoFile.decode(fileName);
    }
}
