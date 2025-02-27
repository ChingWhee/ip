package task;

import exception.TaskException;

import java.util.ArrayList;

/**
 * Manages tasks in the application by adding, updating, and removing tasks.
 * This class interacts with {@code TaskList} to modify and retrieve tasks.
 */
public class TaskManager {
    private TaskList taskList;

    /**
     * Constructs a TaskManager with a given TaskList.
     *
     * @param taskList The TaskList instance to manage tasks.
     * @throws TaskException If an error occurs during initialization.
     */
    public TaskManager(TaskList taskList) throws TaskException {
        this.taskList = taskList;
    }

    /**
     * Changes the completion status of a task.
     *
     * @param isDone Whether the task is marked as done.
     * @param index The 1-based index of the task to update.
     */
    public void changeTaskStatus(Boolean isDone, int index) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        taskList.setStatus(index - 1, isDone);
        System.out.println("\t" + taskList.getTask(index - 1).toString());
    }

    /**
     * Prints all tasks in the task list.
     * If no tasks are available, it prompts the user to add tasks.
     */
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

    /**
     * Adds a new to-do task to the task list.
     *
     * @param description The description of the to-do task.
     */
    public void addTodo(String description) {
        taskList.addTask(new Todo(description));
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param task The task description including the deadline.
     * @throws TaskException If the deadline format is invalid.
     */
    public void addDeadline(String task) throws TaskException {
        if (!task.contains(" /by ")) {
            throw new TaskException("Invalid deadline format! Use: deadline <description> /by <time>");
        }
        String[] deadlineParts = task.split(" /by ", 2);
        taskList.addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param task The event description including start and end times.
     * @throws TaskException If the event format is invalid.
     */
    public void addEvent(String task) throws TaskException {
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
        taskList.addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The 1-based index of the task to be removed.
     */
    public void deleteTask(int index) {
        System.out.println("Alright, I have deleted this task!");
        System.out.println("\t" + taskList.getTask(index - 1).toString());
        taskList.removeTask(index - 1);
        System.out.println("Now you have " + taskList.size() + " tasks.");
    }

    /**
     * Gets the total number of tasks in the list.
     *
     * @return The count of tasks in the task list.
     */
    public int getTasksCount() {
        return taskList.size();
    }

    /**
     * Retrieves the most recently added task.
     *
     * @return A string representation of the latest task.
     */
    public String getLatestTask() {
        return taskList.getLastTask().toString();
    }

    /**
     * Retrieves the full task list.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList.tasks;
    }
}