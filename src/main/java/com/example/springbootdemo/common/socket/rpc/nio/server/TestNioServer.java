package com.example.springbootdemo.common.socket.rpc.nio.server;

import com.example.springbootdemo.common.socket.rpc.nio.ServerHandlerRequestAndResponse;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TestNioServer {

    public static void main(String[] args) {

        try {


            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            SocketAddress socketAddress = new InetSocketAddress(9999);

            serverSocketChannel.bind(socketAddress);

            System.out.println("<<<<<<<<<<<<<<<< 服务器端启动成功 >>>>>>>>>>>>>>>>>>>>");

            Selector selector = Selector.open();

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                int select = selector.select();
                System.out.println("<<<<<<<<<<<<<<<有新的事件触发 >>>>>>>>>>>>>>>>>>>>>>");

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 遍历事件
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {

                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    if (selectionKey.isAcceptable()) {
                        System.out.println("<<<<<<<<<<<<<<<<<<<< 接受请求事件 >>>>>>>>>>>>>>>>>>>");
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel1.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                    } else if (selectionKey.isReadable()) {
                        System.out.println("<<<<<<<<<<<<<<<<<<<< 读事件 >>>>>>>>>>>>>>>>>>>");
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        int readBytes = socketChannel.read(byteBuffer);
                        while (readBytes != -1) {
                            byteBuffer.flip();
                            while (byteBuffer.hasRemaining()) {
                                byte b = byteBuffer.get();
                                byteArrayOutputStream.write(b);
                            }
                            byteBuffer.clear();
                            readBytes = socketChannel.read(byteBuffer);
                            ServerHandlerRequestAndResponse.handlerRequest(byteArrayOutputStream.toString());
                            Object result = "123456";
                            socketChannel.register(selector, SelectionKey.OP_WRITE, result);


                        }




                    } else if (selectionKey.isWritable()) {
                        System.out.println("<<<<<<<<<<<<<<<<<<<< 写事件 >>>>>>>>>>>>>>>>>>>");
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        Object attachment = selectionKey.attachment();
                        ServerHandlerRequestAndResponse.handlerResponse(socketChannel, attachment);


                    }

                }


            }

        } catch (Exception e) {

        }


    }
}
