// package aug12.src.homework;

/**
 * Task class the keeps track
 * of the count, resumes, and
 * stops.
 */
public class Task implements Runnable {
  public int counter;
  private boolean running;

  private final Object lock = new Object();
  private boolean isPaused = true;

  /**
   * Constructor, initialize counter to 1. 
   */
  public Task() {
    this.counter = 1;
    running = true;
  }

  /**
   * Resume executing.
   */
  public void resume() {
    synchronized (lock) {
      isPaused = false;
      lock.notify();
    }
  }

  /**
   * Temporarily stop executing.
   */
  public void pause() {
    synchronized (lock) {
      isPaused = true;
    }
  }

  /**
   * Stop executing.
   */
  public void stop() {
    running = false;
  }


  /**
   * Implement the run function,
   * the task we have to do.
   */
  @Override
  public void run() {
    try {
      while (running) {
        synchronized (lock) {
          while (isPaused) {
            lock.wait();
          }
        }

        System.out.println(this.counter);
        Thread.sleep(2000);
        this.counter++;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
