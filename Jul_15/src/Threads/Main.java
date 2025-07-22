package src.Threads;

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        // min priority of thread -> 1
        // max priority of thread -> 10

        // Thread using lambda function
        Thread thread = new Thread(() -> {
            int sum = 0;

            // System.out.println("Inside the thread");
            for (int i = 0; i < 10000; i++) {
                sum += i;
            }

            // System.out.println(Thread.currentThread().getId() + " " + sum);
        });

        // thread.run(); // not in parallel, just executes what is written in thread

        // thread.start(); // now runs in parallel
        // thread.start();
        /*
         * if start two times,
         * only runs on first time,
         * 2nd time exception,
         * because of thread lifecycle
         * new -> running state (thread.start()) -> maybe blocked -> Terminated
         */

        // thread.run();
        // thread.run();
        // no problem in using thread.run() as many times

        // int mainThreadSum = 0;
        // for (int i = 0; i < 10000; i++) {
        // mainThreadSum += i;
        // }

        // System.out.println(mainThreadSum);
        // System.out.println(Thread.currentThread().getId() + " " + mainThreadSum);

        ////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////

        // CalculateSumThread thread3 = new CalculateSumThread(10);
        // thread3.setName("thread 3");
        // thread3.start();
        // CalculateSumThread thread4 = new CalculateSumThread(10000);
        // thread4.start();
        // System.out.println("hello from " + Thread.currentThread().getName());
        /*
         * Output ->
         * hello from main
         * Thread-1 49995000
         * thead 3 45
         */

        CalculateSumThreadRunnable runnable = new CalculateSumThreadRunnable(10);
        Thread thread5 = new Thread(runnable);
        thread5.start();

    }
}