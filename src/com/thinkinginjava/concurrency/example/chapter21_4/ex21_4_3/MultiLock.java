package com.thinkinginjava.concurrency.example.chapter21_4.ex21_4_3;

import static com.thinkinginjava.util.Print.*;

/**
 * @author: XueYing.Cao
 * @date: 2019/4/30
 * @description:
 */
public class MultiLock {
    public synchronized void f1(int count) {
        if (count-- > 0) {
            print("f1() calling f2() with count " + count);
            f2(count);
        }
    }
    public synchronized void f2(int count) {
        if (count-- > 0) {
            print("f2() calling f1() with count " + count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread(() -> {
            multiLock.f1(10);
        }).start();
    }
}
/**output:
 * f1() calling f2() with count 9
 * f2() calling f1() with count 8
 * f1() calling f2() with count 7
 * f2() calling f1() with count 6
 * f1() calling f2() with count 5
 * f2() calling f1() with count 4
 * f1() calling f2() with count 3
 * f2() calling f1() with count 2
 * f1() calling f2() with count 1
 * f2() calling f1() with count 0
 */