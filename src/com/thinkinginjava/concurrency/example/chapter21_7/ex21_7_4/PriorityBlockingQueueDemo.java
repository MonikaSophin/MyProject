package com.thinkinginjava.concurrency.example.chapter21_7.ex21_7_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.thinkinginjava.util.Print.print;
import static com.thinkinginjava.util.Print.printnb;

/**
 * @author: XueYing.Cao
 * @date: 2019/5/20
 * @description:
 */
class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
    private Random rand = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final int priority;
    protected static List<PrioritizedTask> sequence = new ArrayList<>();
    public PrioritizedTask(int priority) {
        this.priority = priority;
        sequence.add(this);
    }

    @Override
    public int compareTo(PrioritizedTask o) {
        return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
        } catch (InterruptedException e) {
            //~
        }
        print(this);
    }

    @Override
    public String toString() { return String.format("[%1$-3d]", priority) + " Task " + id + ","; }

    public String summary() { return "(" +id + ":" + priority + ")"; }

    public static class EndSentinel extends PrioritizedTask {
        private ExecutorService exec;
        public EndSentinel(ExecutorService exec) {
            super(-1);
            this.exec = exec;
        }
        @Override
        public void run() {
           int count = 0;
            for (PrioritizedTask pt : sequence) {
                printnb(pt.summary());
                if (++count % 5 == 0)
                    print();
            }
            print();
            print(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class PrioritizedTaskProducer implements Runnable {
    private Random rand = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService exec;
    public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
        this.queue = queue;
        this.exec = exec;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            queue.add(new PrioritizedTask(rand.nextInt(10)));
            Thread.yield();
        }
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            for (int i = 0; i < 10; i++)
                queue.add(new PrioritizedTask(i));
            queue.add(new PrioritizedTask.EndSentinel(exec));
        } catch (InterruptedException e) {
            //~
        }
        print("Finished PrioritizedTaskProducer");
    }
}

class PrioritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> queue;
    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted())
                queue.take().run();
        } catch (InterruptedException e) {
            //~
        }
        print("Finished PrioritizedTaskConsumer");
    }
}

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
       // Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
        exec.execute(new PrioritizedTaskProducer(queue, exec));
        exec.execute(new PrioritizedTaskConsumer(queue));
    }
}
/**output:
 * [9  ] Task 5,
 * [9  ] Task 13,
 * [9  ] Task 14,
 * [8  ] Task 10,
 * [8  ] Task 15,
 * [8  ] Task 16,
 * [8  ] Task 0,
 * [8  ] Task 19,
 * [8  ] Task 11,
 * [8  ] Task 6,
 * [7  ] Task 9,
 * [5  ] Task 1,
 * [3  ] Task 2,
 * [2  ] Task 8,
 * [1  ] Task 12,
 * [1  ] Task 17,
 * [1  ] Task 4,
 * [1  ] Task 3,
 * [0  ] Task 7,
 * [0  ] Task 18,
 * [10 ] Task 20,
 * [10 ] Task 21,
 * [10 ] Task 22,
 * [10 ] Task 23,
 * [10 ] Task 24,
 * [10 ] Task 25,
 * [10 ] Task 26,
 * [10 ] Task 27,
 * [10 ] Task 28,
 * Finished PrioritizedTaskProducer
 * [10 ] Task 29,
 * [9  ] Task 39,
 * [8  ] Task 38,
 * [7  ] Task 37,
 * [6  ] Task 36,
 * [5  ] Task 35,
 * [4  ] Task 34,
 * [3  ] Task 33,
 * [2  ] Task 32,
 * [1  ] Task 31,
 * [0  ] Task 30,
 * (0:8)(1:5)(2:3)(3:1)(4:1)
 * (5:9)(6:8)(7:0)(8:2)(9:7)
 * (10:8)(11:8)(12:1)(13:9)(14:9)
 * (15:8)(16:8)(17:1)(18:0)(19:8)
 * (20:10)(21:10)(22:10)(23:10)(24:10)
 * (25:10)(26:10)(27:10)(28:10)(29:10)
 * (30:0)(31:1)(32:2)(33:3)(34:4)
 * (35:5)(36:6)(37:7)(38:8)(39:9)
 * (40:-1)
 * [-1 ] Task 40, Calling shutdownNow()
 * Finished PrioritizedTaskConsumer
 */