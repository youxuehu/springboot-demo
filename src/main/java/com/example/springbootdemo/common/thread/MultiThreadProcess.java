package com.example.springbootdemo.common.thread;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadProcess {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            list.add("hello" + i);
        }
        new MultiThreadProcess().execute(list);
    }

    private void execute(List<String> list) {
        int coreSize = 5;
        ThreadPoolExecutor pool = new ThreadPoolExecutor(coreSize, coreSize, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));
        int totalSize = list.size();
        int start = 0;
        int threadDataSize = totalSize / coreSize;
        int otherSize = totalSize % coreSize;
        List<Future<List<String>>> futureList = new ArrayList<>();
        for (int i = 0; i < coreSize; i++, start += threadDataSize) {
            int end = start + threadDataSize;
            if (i == coreSize - 1) {
                if (otherSize != 0) {
                    end = totalSize;
                }
            }
            Future<List<String>> future = pool.submit(new ExecuteTask(list, start, end));
            futureList.add(future);
        }
        List<String> list2 = new ArrayList<>();
        for (Future<List<String>> future : futureList) {
            try {
                List<String> dataList = future.get();
                list2.addAll(dataList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(JSON.toJSONString(list2, true));
    }


    private class ExecuteTask implements Callable<List<String>> {
        private List<String> list;
        private int start;
        private int end;

        public ExecuteTask(List<String> list, int start, int end) {
            this.list = list;
            this.start = start;
            this.end = end;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> processDataList = new ArrayList<>();
            for (int i = start; i < end; i++) {
                String data = list.get(i);
                System.out.println(Thread.currentThread().getName() + " process data " + data);
                processDataList.add(data);
            }
            return processDataList;
        }
    }
}
