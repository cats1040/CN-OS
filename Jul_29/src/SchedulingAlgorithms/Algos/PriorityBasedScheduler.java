package Jul_29.src.SchedulingAlgorithms.Algos;

import java.util.*;
import Jul_29.src.SchedulingAlgorithms.Model.Task;

public class PriorityBasedScheduler implements Scheduler {
    Map<Task, Thread> taskThreadMap;

    public PriorityBasedScheduler() {
        this.taskThreadMap = new HashMap<>();
    }

    @Override
    public void execute(List<Task> taskList) {
        Comparator<Task> cmp = Comparator.comparingInt(Task::getPriority).reversed();
        PriorityQueue<Task> readyQueue = new PriorityQueue<>(cmp);

        for (Task task : taskList) {
            Thread t = new Thread(task);
            t.start();
            taskThreadMap.put(task, t);
        }

        int currentTime = 0, completed = 0;
        Task currentTask = null;

        while (completed < taskList.size()) {
            for (Task t : taskList) {
                if (t.getArrivalTime() == currentTime) {
                    readyQueue.offer(t);
                }
            }

            if (!readyQueue.isEmpty()) {
                Task highestPriorityTask = readyQueue.peek();

                if (currentTask != null && currentTask != highestPriorityTask) {
                    currentTask.pause();
                }

                if (currentTask != highestPriorityTask) {
                    currentTask = highestPriorityTask;
                    currentTask.resume();
                }

                try {
                    Thread.sleep(100);
                    currentTime++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (currentTask.isCompleted()) {
                    currentTask.calculateTimes(currentTime);
                    readyQueue.remove(currentTask);
                    completed++;
                    currentTask = null;
                } else {
                    try {
                        Thread.sleep(100);
                        currentTime++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (Thread t : taskThreadMap.values()) {
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
