package com.thinkinginjava.concurrency.example.chapter21_7.ex21_7_1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static com.thinkinginjava.util.Print.*;

/**
 * @author: XueYing.Cao
 * @date: 2019/5/17
 * @description:
 */
class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random rand = new Random(47);
    private final CountDownLatch latch;
    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }
    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        print(this + "completed");
    }
    @Override
    public String toString() {
        return String.format("%1$-3d ", id);
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
        }
    }
}

class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;
    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public String toString() {
        return String.format("WaitingTask %1$-3d ", id);
    }

    @Override
    public void run() {
        try {
            latch.await();
            print("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
    }
}

public class CountDownLatchDemo {
    static final int SIZE = 100;
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++)
            exec.execute(new WaitingTask(latch));
        for (int i = 0; i < SIZE; i++)
            exec.execute(new TaskPortion(latch));
        print("Launched all tasks");
        exec.shutdownNow();
    }
}
/**output(Sample):
 * Launched all tasks
 * WaitingTask 4    interrupted
 * WaitingTask 5    interrupted
 * WaitingTask 7    interrupted
 * WaitingTask 3    interrupted
 * WaitingTask 9    interrupted
 * WaitingTask 6    interrupted
 * WaitingTask 1    interrupted
 * WaitingTask 2    interrupted
 * WaitingTask 0    interrupted
 * WaitingTask 8    interrupted
 */