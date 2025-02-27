package task;

import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a list of tasks, providing methods to add, remove, update, and retrieve tasks.
 */
public class TaskList {
    public ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Loads existing task from storage
     *
     * @param storage The storage instance that loads tasks from a file.
     */
    public void load(Storage storage) {
        this.tasks = storage.loadTasks();
    }

    /**
     * Updates the completion status of a task.
     *
     * @param index The index of the task to update.
     * @param status The new completion status (true for done, false for not done).
     */
    public void setStatus(int index, Boolean status) {
        tasks.get(index).setStatus(status);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the last task in the list.
     *
     * @return The most recently added task.
     */
    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The total count of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list by index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Finds tasks that contain all the specified keywords in their description.
     *
     * @param keywords A string containing keywords separated by spaces.
     * @return A list of tasks that contain all keywords.
     */
    public List<Task> findTasksByKeywords(String keywords) {
        // Split input into individual words and check for task with all matching keywords
        String[] keywordList = keywords.toLowerCase().split(" ");

        return tasks.stream()
                .filter(task -> {
                    String description = task.getDescription().toLowerCase();
                    return Arrays.stream(keywordList).allMatch(description::contains);
                })
                .collect(Collectors.toList());
    }

    /**
     * Finds tasks that have deadlines or event start times before a given date.
     *
     * @param date The date to compare against.
     * @return A list of tasks that occur before the specified date.
     */
    public List<Task> findTasksBeforeDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> {
                    if (task instanceof Deadline) {
                        return ((Deadline) task).by.toLocalDate().isBefore(date);
                    } else if (task instanceof Event) {
                        return ((Event) task).from.toLocalDate().isBefore(date);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    /**
     * Finds tasks that have deadlines or event start times after a given date.
     *
     * @param date The date to compare against.
     * @return A list of tasks that occur after the specified date.
     */
    public List<Task> findTasksAfterDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> {
                    if (task instanceof Deadline) {
                        return ((Deadline) task).by.toLocalDate().isAfter(date);
                    } else if (task instanceof Event) {
                        return ((Event) task).from.toLocalDate().isAfter(date);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    /**
     * Finds tasks that have deadlines or event start times on a specific date.
     *
     * @param date The date to compare against.
     * @return A list of tasks that occur on the specified date.
     */
    public List<Task> findTasksOnDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> {
                    if (task instanceof Deadline) {
                        return ((Deadline) task).by.toLocalDate().equals(date);
                    } else if (task instanceof Event) {
                        return ((Event) task).from.toLocalDate().equals(date);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
