package org.example.google.guave.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CommomEventBus {

    EventBus eventBus;

    public void register(Object register) {
        eventBus.register(register);
    }

    public void publish(Object register) {
        eventBus.post(register);
    }

    public void init() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger();
        eventBus = new AsyncEventBus(new ThreadPoolExecutor(5, 25, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "thread-pool" + atomicInteger.incrementAndGet());
                    }
                }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("<<<<<<<<<<  线程池队列已满， 丢弃这个任务，>>>>>>>>>>> Runnable TaskName >>>>>> " + r.toString()
                        + executor.remove(r));
            }
        }), new SubscriberExceptionHandler() {
            @Override
            public void handleException(Throwable throwable, SubscriberExceptionContext subscriberExceptionContext) {
                System.out.println("<<<<<<<<<<<<<<"+throwable.toString() + subscriberExceptionContext.getEvent() +" >>>>>>>>>>>>>>");
            }
        });
    }
}
