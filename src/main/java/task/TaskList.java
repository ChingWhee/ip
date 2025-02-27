package task;

import storage.Storage;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks = new ArrayList<>();

    public void load(Storage storage) {
        this.tasks = storage.loadTasks();
    }

    public void setStatus(int index, Boolean status) {
        tasks.get(index).setStatus(status);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

}
