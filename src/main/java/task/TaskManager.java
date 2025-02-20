package task;

import exception.TaskException;
import java.util.ArrayList;

public class TaskManager {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int tasksCount = 0;

    public static void changeTaskStatus(Boolean isDone, int index) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(index - 1).setStatus(true);
            System.out.println("\t" + tasks.get(index - 1).toString());
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            tasks.get(index - 1).setStatus(false);
            System.out.println("\t" + tasks.get(index - 1).toString());
        }
    }

    public static void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet! Start adding some tasks");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public static int getTasksCount() {
        return tasks.size();
    }

    public static String getLatestTask() {
        return tasks.get(tasks.size() - 1).toString();
    }

    public static void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public static void addDeadline(String task) throws TaskException {
        if (!task.contains(" /by ")) {
            throw new TaskException("Invalid deadline format! Use: deadline <description> /by <time>");
        }
        String[] deadlineParts = task.split(" /by ", 2);
        tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
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
        tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
    }

    public static void deleteTask(int index) {
        System.out.println("Alright, I have deleted this task!");
        System.out.println("\t" + tasks.get(index - 1).toString());
        tasks.remove(index - 1);
        System.out.println("Now you have " + TaskManager.getTasksCount() + " tasks.");
    }
}
