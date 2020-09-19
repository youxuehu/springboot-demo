package org.example.singleton;

/**
 * 懒汉式
 */
public class MusicPlay2 {

    private static MusicPlay2 musicPlay2 = null;

    // 添加互斥锁synchronized，可以保证线程安全， 但是CPU利用率低
    public static synchronized MusicPlay2 getSynchronizedInstance() {
        if (musicPlay2 == null) {
            musicPlay2 = new MusicPlay2();
        }
        return musicPlay2;
    }
}
