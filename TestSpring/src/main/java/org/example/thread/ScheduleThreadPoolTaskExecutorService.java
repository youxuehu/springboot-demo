package org.example.thread;

import com.alibaba.fastjson.JSON;
import org.example.db.dao.bizlock.model.BizLock;
import org.example.db.dao.cleantask.model.CleanTask;
import org.example.db.service.bizlock.BizLockService;
import org.example.db.service.cleantask.CleanTaskService;
import org.example.lock.mysql.MySqlLock;
import org.example.lock.mysql.TestMySqlLock;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class ScheduleThreadPoolTaskExecutorService implements Runnable {

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private ThreadPoolExecutor threadPoolExecutor;
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private CleanTaskService cleanTaskService;
    private BizLockService bizLockService;
    private MySqlLock mySqlLock;
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private static final BizLock DEFAULTLOCK;
    static {
        DEFAULTLOCK = new BizLock();
        DEFAULTLOCK.setVersion(0);
        DEFAULTLOCK.setBizId(TestMySqlLock.BizEnum.THREAD_LOCK.name());
        DEFAULTLOCK.setBizType(TestMySqlLock.BizEnum.THREAD_LOCK.name());
        DEFAULTLOCK.setLockType(TestMySqlLock.BizEnum.THREAD_LOCK_TYPE.name());
    }


    public void init() {
        if (!atomicBoolean.getAndSet(true)) {
            initLock();
            threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10000));
            threadPoolTaskScheduler.scheduleAtFixedRate(this, 5000);
        }
    }

    private void initLock() {
        BizLock bizLock = new BizLock();
        bizLock.setVersion(0);
        bizLock.setBizId(TestMySqlLock.BizEnum.THREAD_LOCK.name());
        bizLock.setBizType(TestMySqlLock.BizEnum.THREAD_LOCK.name());
        bizLock.setLockType(TestMySqlLock.BizEnum.THREAD_LOCK_TYPE.name());
        List<BizLock> bizLockList = bizLockService.queryByBizIdAndBizTypeAndLockType(bizLock.getBizId(),
                                                                                    bizLock.getBizType(),
                                                                                    bizLock.getLockType());
        if (!CollectionUtils.isEmpty(bizLockList)) {
            bizLockService.deleteByIds(bizLockList.stream().map(BizLock::getId).collect(Collectors.toList()));
        }
        bizLockService.insertSlector(bizLock);
    }

    @Override
    public void run() {
        try {
//            if (!mySqlLock.tryLock(DEFAULTLOCK)) {
//                System.out.println("<<<<<< 抢锁失败 >>>>>>");
//                return;
//            }
//            System.out.println("<<<<<< 抢锁成功 >>>>>>");

            threadPoolExecutor.submit(this::cleanExecutionRecord);
            System.out.println("queue size: " + threadPoolExecutor.getQueue().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanExecutionRecord() {
        System.out.println("<<<<<<<<<<<<<  ScheduleThreadPoolTaskExecutorService start   >>>>>>>>>");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<CleanTask> cleanTasks = cleanTaskService.queryByPage(5*100000);
        List<Long> ids = new ArrayList<>();
        long delNum = 0;
        while (!CollectionUtils.isEmpty(cleanTasks)) {
            ids.clear();
            cleanTasks.stream().forEach(item -> {
                ids.add(item.getId());
            });
            int row = cleanTaskService.deleteByIds(ids);
            System.out.println("delete ids :" + row);
            delNum += row;
            cleanTasks = cleanTaskService.queryByPage(5*100000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("<<<<<<<<<<<<< delete total sum : " + delNum + " >>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<  ScheduleThreadPoolTaskExecutorService end   >>>>>>>>>");
//        mySqlLock.releaseLock(DEFAULTLOCK);
//        System.out.println("<<<<<< 释放锁 >>>>>>");
    }

    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    public void setThreadPoolTaskScheduler(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    public void setCleanTaskService(CleanTaskService cleanTaskService) {
        this.cleanTaskService = cleanTaskService;
    }

    public void setBizLockService(BizLockService bizLockService) {
        this.bizLockService = bizLockService;
    }

    public void setMySqlLock(MySqlLock mySqlLock) {
        this.mySqlLock = mySqlLock;
    }
}
