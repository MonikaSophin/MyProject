package com.thinkinginjava.concurrency.exercise.chapter21_2.exercise_07;

public class Daemon implements Runnable {
    Thread [] t = new Thread[10];
    @Override
    public void run() {
        for (int i = 0; i < t.length ; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.println("DaemonSpawn "+i+" started.");
        }
        for (int i = 0; i < t.length ; i++)
            System.out.println("t["+i+"].isDaemon() = "+t[i].isDaemon()+".");
        while (true)
            Thread.yield();
    }
}
