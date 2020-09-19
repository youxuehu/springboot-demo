package org.example.singleton;
public class TestMusicPlay {
    public static void main(String[] args) {
        int i = 0;
        while (i < 10) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MusicPlay3 musicPlay3 = MusicPlay3.getInstance();
                    System.out.println(musicPlay3);
                }
            }).start();
            i++;
        }
    }
}
