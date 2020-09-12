package com.example.springbootdemo.common.socket.rpc.nio.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TestNioClient {

    public static void main(String[] args) {


        for (int i = 0; i < 2; i++) {
            new RequestBioServerTask().start();
        }


    }

    static class RequestBioServerTask extends Thread {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = SocketChannel.open();
                SocketAddress socketAddress = new InetSocketAddress("localhost", 9999);
                socketChannel.connect(socketAddress);

                // 发送数据
                String reqParam = "aa";
                ByteBuffer byteBuffer = ByteBuffer.wrap(reqParam.getBytes());
                socketChannel.write(byteBuffer);

                socketChannel.socket().shutdownOutput();

                ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                // 获取返回结果
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int read = socketChannel.read(readBuffer);
                while (read != -1) {
                    readBuffer.flip();
                    while (readBuffer.hasRemaining()) {
                        byte b = readBuffer.get();
                        byteArrayOutputStream.write(b);

                    }
                    readBuffer.clear();
                    read = socketChannel.read(readBuffer);
                }

                System.out.println("<<<<<<<<<<<<<<<<<<< 获得服务器端响应的结果： " + byteArrayOutputStream.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
