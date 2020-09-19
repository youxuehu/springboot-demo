package org.example.thread;

/**
 * 2 个线程交替执行 1到100
 */
public class JiaotiThreadTest {

    public static void main(String[] args) {
        final Resource resource = new Resource();

        new Thread(){
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    resource.sub();
                }
            }
        }.start();

        for (int i = 1; i <= 50; i++) {
            resource.main();
        }
    }
}

class Resource {
    int count = 0;
    boolean bShouldSub = true;
    public synchronized void sub() {
        while (!bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(++count);

        bShouldSub = false;
        this.notifyAll();

    }

    public synchronized void main() {
        while (bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(++count);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bShouldSub = true;
        this.notifyAll();
    }
}