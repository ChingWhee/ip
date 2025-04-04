package task;

import exception.TaskException;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages tasks in the application by adding, updating, and removing tasks.
 * This class interacts with {@code TaskList} to modify and retrieve tasks.
 */
public class TaskManager {
    private final TaskList taskList;

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
            Ui.printMarkAsDone();
        } else {
            Ui.printMarkAsNotDone();
        }
        taskList.setStatus(index - 1, isDone);
        Ui.printLatestTask(taskList, index);
    }

    /**
     * Prints all tasks in the task list.
     */
    public void printTasks() {
        Ui.printTaskList(taskList);
    }

    /**
     * Adds a new to-do task to the task list.
     *
     * @param description The description of the to-do task.
     */
    public void addTodo(String description) {
        Task task = new Todo(description);
        taskList.addTask(task);
        Ui.printNewTask(task, taskList.size());
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param task The task description including the deadline.
     * @throws TaskException If the deadline format is invalid.
     */
    public void addDeadline(String task) throws TaskException {
        if (!task.contains(" /by ")) {
            throw new TaskException("Invalid deadline format! Use: deadline <description> /by <dd/MM/yyyy HHmm>");
        }
        String[] deadlineParts = task.split(" /by ", 2);
        try {
            Task newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            taskList.addTask(newTask);
            Ui.printNewTask(newTask, taskList.size());
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format! Use: dd/MM/yyyy HHmm (e.g., 15/10/2025 1430).");
        }
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
            throw new TaskException("Invalid event format! Use: event <description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>");
        }

        if (fromIndex > toIndex) {
            throw new TaskException("Invalid order! Ensure /from comes before /to.");
        }

        String[] eventParts = task.split(" /from | /to ", 3);

        if (eventParts.length < 3 || eventParts[1].contains("/to") || eventParts[2].contains("/from")) {
            throw new TaskException("Invalid order! Ensure /from comes before /to.");
        }

        try {
            Task newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
            taskList.addTask(newEvent);
            Ui.printNewTask(newEvent, taskList.size());
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format! Use: dd/MM/yyyy HHmm (e.g., 15/10/2025 1430).");
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The 1-based index of the task to be removed.
     */
    public void deleteTask(int index)  {
        Task task = taskList.getTask(index - 1);
        taskList.removeTask(index - 1);
        Ui.printDeletedTask(task, taskList.size());
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
     * @return The latest task object.
     */
    public Task getLastTask() {
        return taskList.getLastTask();
    }

    /**
     * Retrieves a specific task by index.
     *
     * @param index The index of the task.
     * @return The Task object at the given index.
     */
    public Task getTask(int index) {
        return taskList.getTask(index - 1);
    }

    /**
     * Retrieves the full task list.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList.tasks;
    }

    /**
     * Finds tasks that contain all the specified keywords.
     *
     * @param keywords A string containing keywords separated by spaces.
     * @return A list of tasks that contain all keywords.
     */
    public List<Task> findTasksByKeywords(String keywords) {
        return taskList.findTasksByKeywords(keywords);
    }

    /**
     * Finds tasks that have deadlines or event start times before a given date.
     *
     * @param date The date to compare against.
     * @return A list of tasks that occur before the specified date.
     */
    public List<Task> findTasksBeforeDate(LocalDate date) {
        return taskList.findTasksBeforeDate(date);
    }

    /**
     * Finds tasks that have deadlines or event start times after a given date.
     *
     * @param date The date to compare against.
     * @return A list of tasks that occur after the specified date.
     */
    public List<Task> findTasksAfterDate(LocalDate date) {
        return taskList.findTasksAfterDate(date);
    }

    /**
     * Finds tasks that have deadlines or event start times on a specific date.
     *
     * @param date The date to compare against.
     * @return A list of tasks that occur on the specified date.
     */
    public List<Task> findTasksOnDate(LocalDate date) {
        return taskList.findTasksOnDate(date);
    }
}