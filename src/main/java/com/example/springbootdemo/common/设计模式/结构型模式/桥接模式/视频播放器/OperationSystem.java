package com.example.springbootdemo.common.设计模式.结构型模式.桥接模式.视频播放器;

/**
 * @author youxuehu
 * @version v1.0
 * @className OperationSystem
 * @date 2021/4/18 8:29 下午
 * @desrription 抽象操作系统类 （抽象化角色）
 */
public abstract class OperationSystem {

    protected VideoFile videoFile;

    public OperationSystem(VideoFile videoFile) {
        this.videoFile = videoFile;
    }

    public abstract void play(String fileName);
}
