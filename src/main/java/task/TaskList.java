package task;

import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Task> findTasksByKeywords(String keywords) {
        // Split input into individual words and check for task with all matching keywords
        String[] keywordList = keywords.toLowerCase().split(" ");

        return tasks.stream()
            .filter(task -> {
                String description = task.getDescription().toLowerCase();
                return Arrays.stream(keywordList).allMatch(description::contains);
            })
            .collect(Collectors.toList());
      
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
