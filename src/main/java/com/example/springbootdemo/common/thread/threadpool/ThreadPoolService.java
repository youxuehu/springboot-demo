package com.example.springbootdemo.common.thread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface ThreadPoolService {

    void submit(Runnable runnable);

    <T> Future<T> submit(Callable<T> runnable);

}
