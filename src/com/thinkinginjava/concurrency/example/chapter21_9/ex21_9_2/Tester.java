package com.thinkinginjava.concurrency.example.chapter21_9.ex21_9_2;

import com.thinkinginjava.util.Generated;
import com.thinkinginjava.util.RandomGenerator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: XueYing.Cao
 * @date: 2019/5/30
 * @description:
 */
public abstract class Tester<C> {
    static int testReps = 10;
    static int testCycles = 1000;
    static int containerSize = 1000;
    abstract C containerInitializer();
    abstract void startReadersAndWriters();
    C testContainer;
    String testId;
    int nReaders;
    int nWriters;
    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;
    CountDownLatch endLatch;
    static ExecutorService exec = Executors.newCachedThreadPool();
    Integer[] writeData;
    public Tester(String testId, int nReaders, int nWriters) {
        this.testId = String.format("%s %sr %sw", testId, nReaders, nWriters);
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData = Generated.array(Integer.class, new RandomGenerator.Integer(), containerSize);
        for (int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }
    void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReadersAndWriters();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d\n",
                testId, readTime, writeTime);
        if (readTime != 0 && writeTime != 0)
            System.out.printf("%-27s %14d\n",
                    "readTime + writeTime =", readTime + writeTime);
    }
    abstract class TestTask implements Runnable {
        abstract void test();
        abstract void putResults();
        long duration;
        @Override
        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }
    public static void initMain(int val1, int val2, int val3) {
        testReps = val1;
        testCycles = val2;
        containerSize = val3;
        System.out.printf("%-27s %14s %14s\n",
                "Type", "Read time", "Write time");
    }
}
