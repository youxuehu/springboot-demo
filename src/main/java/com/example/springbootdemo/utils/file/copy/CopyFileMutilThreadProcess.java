package com.example.springbootdemo.utils.file.copy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CopyFileMutilThreadProcess {

    static final int copySize = 10000;

    public static void main(String[] args) throws Exception {

        File file1 = new File("/Users/youxuehu/The_Man_of_Property.txt");
        File file2 = new File("/Users/youxuehu/IdeaProjects/springboot-demo/src/main/java/com/example/springbootdemo/utils/file/copy/2.txt");

        RandomAccessFile randomAccessFile1 = new RandomAccessFile(file1, "r");
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");


        long length = randomAccessFile1.length();
        randomAccessFile2.setLength(length);

        int bord = (int) (length / copySize);
        bord = (int) (length % copySize) == 0 ? bord : bord + 1;
        ThreadPoolExecutor pool = new ThreadPoolExecutor(bord, bord, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));
        try {
            for (int i = 1; i < bord; i++) {
                pool.execute(new CopyFileThread(file1, file2, i * copySize * 1024));
            }
            randomAccessFile1.seek(0);
            randomAccessFile2.seek(0);
            byte[] bytes = new byte[1024];
            int len = 0;
            int maxSize = 0;
            while ((len = randomAccessFile1.read(bytes)) != -1 && maxSize < copySize) {
                randomAccessFile2.write(bytes, 0, len);
                maxSize++;
            }
            randomAccessFile1.close();
            randomAccessFile2.close();
            Thread.sleep(3000);
            System.out.println("复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

    }

    private static class CopyFileThread implements Runnable {

        private RandomAccessFile randomAccessFile1;
        private RandomAccessFile randomAccessFile2;
        private long startPoint;

        public CopyFileThread(File file1, File file2, long startPoint) {
            try {
                this.randomAccessFile1 = new RandomAccessFile(file1, "r");
                this.randomAccessFile2 = new RandomAccessFile(file2, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            this.startPoint = startPoint;
        }

        @Override
        public void run() {
            try {
                randomAccessFile1.seek(startPoint);
                randomAccessFile2.seek(startPoint);
                byte[] bytes = new byte[1024];
                int len = 0;
                int maxSize = 0;
                while ((len = randomAccessFile1.read(bytes)) != -1 && maxSize < copySize) {
                    randomAccessFile2.write(bytes, 0, len);
                    maxSize++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    randomAccessFile1.close();
                    randomAccessFile2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
