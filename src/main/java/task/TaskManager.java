package task;

import exception.TaskException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskManager {
    private TaskList taskList;

    public TaskManager(TaskList taskList) throws TaskException {
        this.taskList = taskList;
    }

    public void changeTaskStatus(Boolean isDone, int index) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        taskList.setStatus(index - 1, isDone);
        System.out.println("\t" + taskList.getTask(index - 1).toString());
    }

    public void printTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks yet! Start adding some tasks");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i) != null) {
                System.out.println((i + 1) + "." + taskList.getTask(i));
            }
        }
    }

    public void addTodo(String description) {
        taskList.addTask(new Todo(description));
    }

    public void addDeadline(String task) throws TaskException {
        if (!task.contains(" /by ")) {
            throw new TaskException("Invalid deadline format! Use: deadline <description> /by <dd/MM/yyyy HHmm>");
        }
        String[] deadlineParts = task.split(" /by ", 2);
        try {
            taskList.addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format! Use: dd/MM/yyyy HHmm (e.g., 15/10/2025 1430).");
        }
    }

    public void addEvent(String task) throws TaskException {
        int fromIndex = task.indexOf(" /from ");
        int toIndex = task.indexOf(" /to ");

        // Check if both "/from" and "/to" exist
        if (fromIndex == -1 || toIndex == -1) {
            throw new TaskException("Invalid event format! Use: event <description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>");
        }

        // Ensure "/from" appears before "/to"
        if (fromIndex > toIndex) {
            throw new TaskException("Invalid order! Ensure /from comes before /to.");
        }

        // Split into description, from, and to
        String[] eventParts = task.split(" /from | /to ", 3);

        // Ensure extracted values do not contain misplaced keywords
        if (eventParts.length < 3 || eventParts[1].contains("/to") || eventParts[2].contains("/from")) {
            throw new TaskException("Invalid order! Ensure /from comes before /to.");
        }

        try {
            taskList.addTask(new Event(eventParts[0], eventParts[1], eventParts[2])); // Parses date inside Event constructor
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format! Use: dd/MM/yyyy HHmm (e.g., 15/10/2025 1430).");
        }
    }

    public void deleteTask(int index) {
        System.out.println("Alright, I have deleted this task!");
        System.out.println("\t" + taskList.getTask(index - 1).toString());
        taskList.removeTask(index - 1);
        System.out.println("Now you have " + taskList.size() + " tasks.");
    }

    public int getTasksCount() {
        return taskList.size();
    }

    public String getLatestTask() {
        return taskList.getLastTask().toString();
    }

    public ArrayList<Task> getTaskList() {
        return taskList.tasks;
    }
}