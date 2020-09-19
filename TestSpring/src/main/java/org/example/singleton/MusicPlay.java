package org.example.singleton;

/**
 * 饿汉式
 */
public class MusicPlay {

    private MusicPlay() {}

    private static final MusicPlay musicPlay = new MusicPlay();

    public static  MusicPlay getInstance() {
        return musicPlay;
    }
}
