package Jul_29.src.SchedulingAlgorithms.Main;

import java.util.*;

import Jul_29.src.SchedulingAlgorithms.Algos.PriorityBasedScheduler;
import Jul_29.src.SchedulingAlgorithms.Algos.RoundRobinScheduler;
import Jul_29.src.SchedulingAlgorithms.Algos.SRTFScheduler;
import Jul_29.src.SchedulingAlgorithms.Model.Task;

public class Main {
    public static void rrs() {
        List<Task> testTask = new ArrayList<>();
        testTask.add(new Task(1, 0, 5, 1));
        testTask.add(new Task(2, 1, 4, 1));
        testTask.add(new Task(3, 2, 2, 1));
        testTask.add(new Task(4, 4, 1, 1));

        RoundRobinScheduler rrs = new RoundRobinScheduler(2);

        try {
            rrs.execute(testTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void srtfs() {
        List<Task> testTask = new ArrayList<>();
        testTask.add(new Task(1, 0, 6, 1));
        testTask.add(new Task(2, 0, 8, 1));
        testTask.add(new Task(3, 0, 5, 1));

        SRTFScheduler srtfs = new SRTFScheduler();

        try {
            srtfs.execute(testTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pbs() {
        List<Task> testTask = new ArrayList<>();
        testTask.add(new Task(1, 0, 4, 1));
        testTask.add(new Task(2, 1, 2, 1));
        testTask.add(new Task(3, 2, 6, 1));

        PriorityBasedScheduler pbs = new PriorityBasedScheduler();

        try {
            pbs.execute(testTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // srtfs();
        pbs();
    }
}
