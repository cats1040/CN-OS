package src.Threads;

public class CalculateSumThread extends Thread {
    private int sumUpTo;

    public CalculateSumThread(int sumUpTo) {
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
