package Jul_29.src.SchedulingAlgorithms.Algos;

import Jul_29.src.SchedulingAlgorithms.Model.Task;
import java.util.*;

public class RoundRobinScheduler implements Scheduler {
    int timeQuantum;
    Map<Task, Thread> taskThreadMap;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
        this.taskThreadMap = new HashMap<>();
    }

    @Override
    public void execute(List<Task> taskList) {
        Queue<Task> q = new LinkedList<>();
        for (Task task : taskList) {
            // All threads start but are paused —
            // they won’t run until resumed by the
            // scheduler.
            Thread thread = new Thread(task, "Task-" + task.getTaskId() + "-" + task.getArrivalTime());
            thread.start();
            taskThreadMap.put(task, thread);
        }

        int currentTime = 0, completed = 0, idx = 0;

        while (completed < taskList.size()) {
            // Add new tasks, based on current time
            while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                q.offer(taskList.get(idx));
                idx++;
            }

            try {
                if (q.isEmpty()) {
                    currentTime++;
                    Thread.sleep(100);
                    continue;
                }

                Task current = q.poll();
                int time = 0;

                // t1 -8 , t4 , t5
                while (time < timeQuantum && !current.isCompleted()) {
                    current.resume();
                    Thread.sleep(100);
                    currentTime++;
                    time++;

                    while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                        q.offer(taskList.get(idx));
                        idx++;
                    }
                }

                if (current.isCompleted()) {
                    if (current.getCompletionTime() == 0) {
                        System.out.println("Task Completed :" + current.getTaskId());
                        completed++;
                        current.calculateTimes(currentTime);
                    }
                } else {
                    q.offer(current);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (

        Thread t : taskThreadMap.values()) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        printStatistic(taskList);
    }

    private void printStatistic(List<Task> taskList) {
        System.out.println("\nTask | Completion | Turnaround | Waiting");
        for (Task t : taskList) {
            System.out.printf(" %2d  |     %2d     |     %2d     |    %2d\n",
                    t.getTaskId(), t.getCompletionTime(),
                    t.getTurnaroundTime(), t.getWaitingTime());
        }
    }
}
