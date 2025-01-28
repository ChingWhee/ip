package task;

public class TaskManager {
    public static Task[] tasks = new Task[100];
    public static int tasksCount = 0;

    public static void addTask(String name) {
        tasks[tasksCount] = new Task(name);
        tasksCount++;
    }

    public static void changeTaskStatus(Boolean status, int index) {
        if (status) {
            System.out.println("Nice! I've marked this task as done:");
            tasks[index - 1].setStatus(true);
            System.out.println("\t[" + tasks[index - 1].getStatusIcon() + "] "
                    + tasks[index - 1].getDescription());
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            tasks[index - 1].setStatus(false);
            System.out.println("\t[" + tasks[index - 1].getStatusIcon() + "] "
                    + tasks[index - 1].getDescription());
        }
    }

    public static void printTasks() {
        for (int i = 0; i < tasksCount; i++) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            }
        }
    }

    public static int getTasksCount() {
        return tasksCount;
    }
}
