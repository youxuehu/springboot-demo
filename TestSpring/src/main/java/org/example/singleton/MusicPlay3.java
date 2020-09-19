package org.example.singleton;

public class MusicPlay3 {

    // 静态内部类可以实现线程安全
    private static class CreateInstance {
        // 保证单例
        static MusicPlay3 musicPlay3 = new MusicPlay3();
    }

    public static MusicPlay3 getInstance() {
        return CreateInstance.musicPlay3;
    }
}
