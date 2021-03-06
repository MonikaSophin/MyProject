package com.thinkinginjava.concurrency.exercise.chapter21_2.exercise_09;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePrioritiesEx09 implements Runnable {
    private int countDown = 5;
    private volatile double d;
    public SimplePrioritiesEx09() {
    }

    @Override
    public String toString() {
        return Thread.currentThread()+": "+countDown;
    }

    @Override
    public void run() {
        while (true){
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E)/(double) i;
                if (i%1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool(
                new SimplePrioritiesThradFactory());
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePrioritiesEx09());
        }
    }
}
