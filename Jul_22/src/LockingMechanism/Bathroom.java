package Jul_22.src.LockingMechanism;

import java.util.concurrent.Semaphore;

public class Bathroom {
    // Allow max 3 people at a time
    private static Semaphore stalls = new Semaphore(3);

    public static void useBathroom(String person) {
        try {
            System.out.println(person + " is trying to enter...");

            // Tries to acquire a stall (permit)
            stalls.acquire();
            System.out.println(person + " entered the bathroom.");

            Thread.sleep(1000); // using the bathroom

            System.out.println(person + " is leaving.");
            stalls.release(); // free up a stall
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            final String person = "Person-" + i;
            new Thread(() -> useBathroom(person)).start();
        }
    }
}
