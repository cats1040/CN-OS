// package aug12.src.homework;

import java.util.Scanner;

/**
 * Main class.
 */
public class Main {
  /**
   * main function to run the
   * thread, and execute the task. 
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Task task = new Task();
    Thread thread = new Thread(task);
    thread.start();

    while (true) {
      String input = sc.nextLine();

      if (input.equals("start")) {
        task.resume();
      } else if (input.equals("stop")) {
        task.pause();
      } else if (input.equals("exit")) {
        task.stop();
        break;
      } else {
        System.out.println("ERROR! Invalid option!");
      }
    }

    sc.close();

    return;
  }
}
