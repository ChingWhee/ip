package task;

import storage.Storage;

import java.util.ArrayList;

/**
 * Manages a list of tasks, providing methods to add, remove, update, and retrieve tasks.
 */
public class TaskList {
    public ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Loads tasks from storage and replaces the current task list.
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
}
