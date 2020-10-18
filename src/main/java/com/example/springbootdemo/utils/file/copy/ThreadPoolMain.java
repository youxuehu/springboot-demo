package com.example.springbootdemo.utils.file.copy;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class ThreadPoolMain {
	
	public void copyFile(String srcPath, String descPath, int corePoolSize) {
		long start = System.currentTimeMillis();
		try {
			new ThreadPoolMain().process(srcPath, descPath, corePoolSize, start);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void process(String srcPath, String descPath, int corePoolSize, long start) throws Exception {
		File file1 = new File(srcPath);
		File file2 = new File(descPath);
		if (!file2.exists()) {
			file2.createNewFile();
		}
		RandomAccessFile randomAccess1 = new RandomAccessFile(file1, "r"); 
		RandomAccessFile randomAccess2 = new RandomAccessFile(file2, "rw"); 
		
		long length = randomAccess1.length(); 
		randomAccess2.setLength(length);
		int copySize = (int) (length / corePoolSize);
		corePoolSize = (int) (length % corePoolSize) == 0 ? corePoolSize : corePoolSize + 1;
		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, corePoolSize, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
		final CountDownLatch countDownLatch = new CountDownLatch(corePoolSize);
		try {
			for (int i = 0; i < corePoolSize; i++) {
				pool.execute(new CopyThread(countDownLatch, file1, file2, copySize, i * copySize));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 不再接收新的任务， 所有执行完毕后销毁线程
			pool.shutdown();
		}
		countDownLatch.await();
		System.out.println("复制完成");
		long end = System.currentTimeMillis() - start;
		System.out.println("总耗时：" + end + "毫秒 --> " + end / 1000 + "秒");
	}
	
	private class CopyThread implements Runnable {
		private CountDownLatch countDownLatch;
		private RandomAccessFile randomAccess1;
		private RandomAccessFile randomAccess2;
		private int copySize;
		private int startPointer;

		public CopyThread(CountDownLatch countDownLatch, File file1, File file2, int copySize, int startPointer) throws Exception {
			super();
			this.countDownLatch = countDownLatch;
			this.randomAccess1 = new RandomAccessFile(file1, "r");
			this.randomAccess2 = new RandomAccessFile(file2, "rw");
			this.copySize = copySize;
			this.startPointer = startPointer;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " --> startPointer:" + startPointer);
			try {
				randomAccess1.seek(startPointer);
				randomAccess2.seek(startPointer);
				int len;
				byte[] bytes = new byte[1024];
				int count = 0;
				while ((len = randomAccess1.read(bytes)) != -1 && count < copySize) {
//					System.out.println(Thread.currentThread().getName() + " --> len --> " + len);
					randomAccess2.write(bytes, 0, len);
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					randomAccess1.close();
					randomAccess2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			}
		}
	}
}
