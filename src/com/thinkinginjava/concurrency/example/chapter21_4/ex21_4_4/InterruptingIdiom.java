package com.thinkinginjava.concurrency.example.chapter21_4.ex21_4_4;

import java.util.concurrent.TimeUnit;

import static com.thinkinginjava.util.Print.*;

/**
 * @author: XueYing.Cao
 * @date: 2019/4/30
 * @description:
 */
class NeedsCleanup {
    private final int id;
    public NeedsCleanup(int id) {
        this.id = id;
        print("NeedsCleanup " + id);
    }
    public void cleanup() {
        print("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // point1
                NeedsCleanup n1 = new NeedsCleanup(1);
                // 在定义n1后立即启动try-finally，以保证正确清理n1:
                try {
                    print("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    // point2
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    // 保证正确清理n2:
                    try {
                        print("Calculating");
                        // 耗时，无阻塞的操作:
                        for(int i = 1; i < 2500000; i++)
                            d = d + (Math.PI + Math.E) / d;
                        print("Finished time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            print("Exiting via while() test");
        } catch(InterruptedException e) {
            print("Exiting via InterruptedException");
        }
    }
}

public class InterruptingIdiom {
    public static void main(String[] args) throws Exception {
//        if(args.length != 1) {
//            print("usage: java InterruptingIdiom delay-in-mS");
//            System.exit(1);
//        }
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(1100);
        t.interrupt();
    }
}
/**output:
 * NeedsCleanup 1
 * Sleeping
 * NeedsCleanup 2
 * Calculating
 * Finished time-consuming operation
 * Cleaning up 2
 * Cleaning up 1
 * NeedsCleanup 1
 * Sleeping
 * Cleaning up 1
 * Exiting via InterruptedException
 */