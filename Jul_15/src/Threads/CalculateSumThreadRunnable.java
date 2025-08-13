package src.Threads;

/* 
 it just implements runnable, 
 we have to provide run function,
 otherwise it will not run
 it is not a thread, it just tells
 the thread what to run
 */
public class CalculateSumThreadRunnable implements Runnable {
    private int sumUpTo;

    public CalculateSumThreadRunnable(int sumUpTo) {
        this.sumUpTo = sumUpTo;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < sumUpTo; i++) {
            sum += i;
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " " + sum);
    }
}
