package com.example.springbootdemo.common.socket.rpc.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerHandlerRequestAndResponse {

    public static void handlerRequest(String request) {

        if ("aa".equals(request)) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("<<<<<<<<<<<<<<<<<接受客户端的请求参数： " +request + " >>>>>>>>>>>>>>>>>>>>>>>");

    }

    public static void handlerResponse(SocketChannel socketChannel, Object atta) {

        try {
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<< 附件内容为： " + atta + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
            String formatDateTime = dateTimeFormatter.format(LocalDateTime.now());
            ByteBuffer byteBuffer = ByteBuffer.wrap(formatDateTime.getBytes());
            socketChannel.write(byteBuffer);
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
