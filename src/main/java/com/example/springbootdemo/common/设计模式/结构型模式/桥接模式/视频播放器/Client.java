package com.example.springbootdemo.common.设计模式.结构型模式.桥接模式.视频播放器;

/**
 * @author youxuehu
 * @version v1.0
 * @className Client
 * @date 2021/4/18 8:33 下午
 * @desrription 桥接模式
 */
public class Client {
    public static void main(String[] args) {
        OperationSystem system = new Mac(new AviFile());
        system.play("哥斯拉大战金刚.avi");
    }
}
