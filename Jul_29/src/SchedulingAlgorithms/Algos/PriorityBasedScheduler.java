import java.util.*;
// import Jul_29.src.SchedulingAlgorithms.Model.Task;

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
            Thread t = new Thread(task, "Task-" + task.getTaskId());
            t.start();
            taskThreadMap.put(task, t);
        }

        int currentTime = 0, completed = 0, idx = 0;
        Task currentTask = null;

        while (completed < taskList.size()) {
            while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                readyQueue.offer(taskList.get(idx));
                idx++;
            }

            if (!readyQueue.isEmpty()) {
                Task highestPriorityTask = readyQueue.poll();

                currentTask = highestPriorityTask;

                currentTask.resume();

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
                } else {
                    readyQueue.offer(currentTask);
                }
            } else {
                currentTime++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
