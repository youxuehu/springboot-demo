package com.example.springbootdemo.common.socket.rpc.bio.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class TestBioSockerClient {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new RequestServerTask().start();
        }

    }

    static class RequestServerTask extends Thread {
        @Override
        public void run() {
            try {

                Socket socket = new Socket();
                SocketAddress socketAddress = new InetSocketAddress("localhost", 9999);

                socket.connect(socketAddress);

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                PrintWriter printWriter = new PrintWriter(outputStream);

                printWriter.println("what is your name?");
                printWriter.flush();


                String result = bufferedReader.readLine();

                System.out.println("请求结果为：" + result);

                printWriter.close();

                bufferedReader.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
