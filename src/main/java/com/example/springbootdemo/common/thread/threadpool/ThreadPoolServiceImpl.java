package com.example.springbootdemo.common.thread.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池创建
       public ThreadPoolExecutor(int corePoolSize, // 线程数量
                                  int maximumPoolSize, // 最大的线程数量
                                 long keepAliveTime, // 当有空闲的线程时， 设置销毁的时间
                                 TimeUnit unit, // 单位
                                 BlockingQueue<Runnable> workQueue, //  存储线程的容器
                                 ThreadFactory threadFactory, // 创建线程的工厂
                                 RejectedExecutionHandler handler) { // 当队列满了，怎么处理，默认拒绝任务，抛异常
 */
@Service
public class ThreadPoolServiceImpl implements ThreadPoolService, InitializingBean {

    static Logger LOG = LoggerFactory.getLogger(ThreadPoolServiceImpl.class);

    private ThreadPoolExecutor threadPoolExecutor;

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Override
    public void submit(Runnable runnable) {
        threadPoolExecutor.submit(runnable);
    }

    @Override
    public <T> Future<T> submit(Callable<T> callable) {
        return threadPoolExecutor.submit(callable);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        threadPoolExecutor = new ThreadPoolExecutor(5, 5, 3600, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "my-thread-pool" + atomicInteger.getAndIncrement());
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                throw new RejectedExecutionException("Task " + r.toString() +
                        " rejected from " + executor.toString());
            }
        });
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new TimerTask() {
            @Override
            public void run() {
                LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>thread active count = " + threadPoolExecutor.getActiveCount());
                LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>thread task count = " + threadPoolExecutor.getTaskCount());
                LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>thread completed task count = " + threadPoolExecutor.getCompletedTaskCount());
            }
        }, 5, 5, TimeUnit.SECONDS);
    }
}
