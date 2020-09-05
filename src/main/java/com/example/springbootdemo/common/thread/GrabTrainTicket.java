package com.example.springbootdemo.common.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 抢火车票
 */
public class GrabTrainTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ConsumerTicket consumerTicket = new ConsumerTicket(ticket);
        ConsumerTicket consumerTicket2 = new ConsumerTicket(ticket);
        ConsumerTicket consumerTicket3 = new ConsumerTicket(ticket);
        ConsumerTicket consumerTicket4 = new ConsumerTicket(ticket);
        ConsumerTicket consumerTicket5 = new ConsumerTicket(ticket);
        ConsumerTicket consumerTicket6 = new ConsumerTicket(ticket);




        ProviderTicket providerTicket = new ProviderTicket(ticket);
        ProviderTicket providerTicket2 = new ProviderTicket(ticket);
        ProviderTicket providerTicket3 = new ProviderTicket(ticket);
        ProviderTicket providerTicket4 = new ProviderTicket(ticket);
        ProviderTicket providerTicket5 = new ProviderTicket(ticket);
        ProviderTicket providerTicket6 = new ProviderTicket(ticket);



//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor
//                        (2,
//                        4,
//                        100,
//                        TimeUnit.SECONDS,
//                        new ArrayBlockingQueue<Runnable>(10000));
//        threadPool.submit(consumerTicket);
//        threadPool.submit(consumerTicket2);
//        threadPool.submit(consumerTicket3);
//        threadPool.submit(consumerTicket4);
//        threadPool.submit(consumerTicket5);
//        threadPool.submit(consumerTicket6);
//
//
//        threadPool.submit(providerTicket);
//        threadPool.submit(providerTicket2);
//        threadPool.submit(providerTicket3);
//        threadPool.submit(providerTicket4);
//        threadPool.submit(providerTicket5);
//        threadPool.submit(providerTicket6);

//        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
//
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 2, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 2, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 2, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(consumerTicket, 0, 2, TimeUnit.SECONDS);
//
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(providerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(providerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(providerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(providerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(providerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(providerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(providerTicket, 0, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleWithFixedDelay(providerTicket, 0, 1, TimeUnit.SECONDS);

        while(true) {
            ConsumerTicket consumerTicket100 = new ConsumerTicket(ticket);
            consumerTicket100.start();
            ConsumerTicket consumerTicket101 = new ConsumerTicket(ticket);
            consumerTicket101.start();
            ConsumerTicket consumerTicket102 = new ConsumerTicket(ticket);
            consumerTicket102.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ProviderTicket providerTicket100 = new ProviderTicket(ticket);
            providerTicket100.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConsumerTicket extends Thread {

    private Ticket ticket;

    public ConsumerTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        ticket.grab();
    }
}

class ProviderTicket extends Thread {

    private Ticket ticket;

    public ProviderTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        ticket.push();
    }
}

class Ticket {
    /**
     * 总共6张火车票
     */
    List<Integer> list = new ArrayList<Integer>(){{
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
        add(7);
        add(8);
        add(9);
        add(10);
    }};

    public synchronized void grab() {
        while (list.size() == 0) {
            System.err.println(Thread.currentThread().getName() + "  --- 抢票失败， 没有剩余的票了 ----");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.remove(0);
        System.out.println(Thread.currentThread().getName() + "  --- 抢票成功 -----    剩余的火车票 --- " + list.toString());
        this.notifyAll();
    }

    public synchronized void push() {
        while(list.size() == 10) {
            try {
                this.wait();
                System.err.println(Thread.currentThread().getName() + "  --- 票满了 -----     ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(100);
        System.out.println(Thread.currentThread().getName() + " --- 加票成功  --- 100 剩余的火车票 --- " + list.toString());
        this.notifyAll();
    }
}
