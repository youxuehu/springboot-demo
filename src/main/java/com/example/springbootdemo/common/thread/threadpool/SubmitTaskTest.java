package com.example.springbootdemo.common.thread.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TimerTask;
import java.util.concurrent.*;

@Service
public class SubmitTaskTest implements InitializingBean {

    static Logger LOG = LoggerFactory.getLogger(SubmitTaskTest.class);

    @Autowired
    private ThreadPoolService threadPoolService;

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Override
    public void afterPropertiesSet() throws Exception {
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new TimerTask() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    int finalI = i;
//                    threadPoolService.submit(new Runnable() {
//                        @Override
//                        public void run() {
//                            LOG.info("submit task to thread pool " + finalI);
//                        }
//                    });
                    Future<String> result = threadPoolService.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return "<<<<<<<<<<<<<<<<<<<<<<<<<<<<task [" + finalI + "] execute success>>>>>>>>>>>>>>>>>>>>>>>>>>>";
                        }
                    });
                    try {
                        String message = result.get();
                        LOG.info(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 3, 3, TimeUnit.SECONDS);
    }
}
