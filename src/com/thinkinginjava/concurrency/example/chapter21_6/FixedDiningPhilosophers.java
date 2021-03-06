package com.thinkinginjava.concurrency.example.chapter21_6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: XueYing.Cao
 * @date: 2019/5/17
 * @description:
 */
public class FixedDiningPhilosophers {
    public static void main(String[] args) throws InterruptedException {
        int ponder = 5;
        int size = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++)
            sticks[i] = new Chopstick();

        for (int i = 0; i < size; i++)
            if (i < size - 1) {
                exec.execute(new Philosopher(sticks[i], sticks[i + 1], i, ponder));
            } else {
                exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
            }
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
