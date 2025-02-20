package task;

import exception.TaskException;

public class TaskManager {
    public static Task[] tasks = new Task[100];
    public static int tasksCount = 0;

    public static void changeTaskStatus(Boolean isDone, int index) {
        if (isDone) {
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
        if (tasksCount == 0) {
            System.out.println("No tasks yet! Start adding some tasks");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksCount; i++) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
    }

    public static int getTasksCount() {
        return tasksCount;
    }

    public static String getLatestTask() {
        return tasks[tasksCount - 1].toString();
    }

    public static void addTodo(String description) {
        tasks[tasksCount] = new Todo(description);
        tasksCount++;
    }

    public static void addDeadline(String task) throws TaskException {
        if (!task.contains(" /by ")) {
            throw new TaskException("Invalid deadline format! Use: deadline <description> /by <time>");
        }
        String[] deadlineParts = task.split(" /by ", 2);
        tasks[tasksCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
        tasksCount++;
    }

    public static void addEvent(String task) throws TaskException {
        int fromIndex = task.indexOf(" /from ");
        int toIndex = task.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new TaskException("Invalid event format! Use: event <description> /from <start> /to <end>");
        }
        if (fromIndex > toIndex) {
            throw new TaskException("Invalid order! Ensure /from comes before /to.");
        }

        String[] eventParts = task.split(" /from | /to ", 3);
        if (eventParts[1].contains("/to") || eventParts[2].contains("/from")) {
            throw new TaskException("Invalid order! Ensure /from comes before /to.");
        }
        tasks[tasksCount] = new Event(eventParts[0], eventParts[1], eventParts[2]);
        tasksCount++;
    }
}
