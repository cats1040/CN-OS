package Jul_22.src.LockingMechanism;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

// public class Main {
//     static class Counter {
//         int count;
//         ReentrantLock reentrantLock;

//         Counter() {
//             this.count = 0;
//             this.reentrantLock = new ReentrantLock();
//         }

//         // Not thread-friendly
//         // void increaseCount() {
//         // this.count++;
//         // }

//         /*
//          * 1st way of using mutex
//          * adding key word synchronized
//          * this method will be used by one
//          * thread at a time
//          */
//         // synchronized void increaseCount() {
//         // this.count++;
//         // }

//         void increaseCount() {
//             while (true) {
//                 // tryLock checks is lock is free
//                 if (this.reentrantLock.tryLock()) {
//                     this.count++;
//                     this.reentrantLock.unlock();
//                     break;
//                 }
//             }
//         }
//     }

//     static class UpdateCounterUptoNThread extends Thread {
//         Counter counter;
//         int N;

//         UpdateCounterUptoNThread(Counter counter, int N) {
//             this.counter = counter;
//             this.N = N;
//         }

//         @Override
//         public void run() {
//             for (int i = 0; i < N; i++) {
//                 counter.increaseCount();
//             }
//         }
//     }

//     public static void main(String[] args) throws InterruptedException {
//         int N = 10000000, N2 = 10000000;
//         Counter counter = new Counter();
//         UpdateCounterUptoNThread thread1 = new UpdateCounterUptoNThread(counter, N);
//         UpdateCounterUptoNThread thread2 = new UpdateCounterUptoNThread(counter, N2);

//         // thread1.start();
//         // /*
//         // * before the thread completes its task
//         // * main function moves to next line
//         // * and prints zero, so to avoid this
//         // * thread1.start();
//         // * thread1.join();
//         // * now the main function waits
//         // * for the thread1 to finish
//         // */
//         // // System.out.println(counter.count); // 0

//         // System.out.println("Between start and join of thread1");

//         // thread1.join();
//         // System.out.println(counter.count); // 100

//         thread1.start();
//         thread2.start();
//         thread1.join();
//         thread2.join();

//         System.out.println(counter.count);

//         /*
//          * When N, N2 is very large
//          * Problem beacuse of not good
//          * resource management
//          * Reader-Writer problem
//          * 
//          * Solution -> Mutex, Lock, Semaphore
//          * Mutex is just like a flag, on/off
//          * Mutex - 1. Java own way lock
//          * 2. Java provides its own
//          * mutex class
//          * 
//          * Sempahore is more advanced,
//          */
//     }
// }

/*
    Instead of making a counter class,
    Java provides AtomicInteger which
    does the same thing
*/
public class Main {

    static class UpdateCounterUptoNThread extends Thread {
        AtomicInteger counter;
        int N;

        UpdateCounterUptoNThread(AtomicInteger counter, int N) {
            this.counter = counter;
            this.N = N;
        }

        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                counter.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 10000000, N2 = 10000000;
        AtomicInteger counter = new AtomicInteger(0);
        UpdateCounterUptoNThread thread1 = new UpdateCounterUptoNThread(counter, N);
        UpdateCounterUptoNThread thread2 = new UpdateCounterUptoNThread(counter, N2);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(counter.get());
    }
}
