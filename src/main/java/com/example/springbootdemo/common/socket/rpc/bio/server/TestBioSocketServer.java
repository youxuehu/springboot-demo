package com.example.springbootdemo.common.socket.rpc.bio.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestBioSocketServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.printf("<<<<<<<<<<<<<<<<<<服务器启动中>>>>>>>>>>>>>>>>>");

        AtomicInteger atomicInteger = new AtomicInteger();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 36000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-pool" + atomicInteger.getAndIncrement());
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                throw new RejectedExecutionException("server reject process" + executor.toString());
            }
        });

        while(true) {

            Socket socket = serverSocket.accept();
            System.out.println("<<<<<<<<<<<<<<<<有请求进来了>>>>>>>>>>>>>>>>");
            TaskExecution taskExecution = new TaskExecution(socket);
            threadPoolExecutor.submit(taskExecution);
        }

    }
}

class TaskExecution extends Thread {
    private Socket socket;

    public TaskExecution(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            PrintWriter printWriter = new PrintWriter(outputStream);

            String reqParam = bufferedReader.readLine();
            System.out.println("<<<<<<<<<<<<服务端接受到请求参数>>>>>>>>>>>>");
            System.out.println("请求参数：" + reqParam);


            if ("aa".equals(reqParam)) {
                Thread.sleep(10000);
            }

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
            String formatDateTime = dateTimeFormatter.format(LocalDateTime.now());

            printWriter.println(formatDateTime);
            printWriter.flush();
            printWriter.close();

            bufferedReader.close();

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}