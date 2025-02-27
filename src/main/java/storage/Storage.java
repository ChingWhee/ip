package storage;

import java.io.*;
import java.util.ArrayList;
import exception.TaskException;
import task.*;

public class Storage {
    private final String currentDir = System.getProperty("user.dir");
    private final String FILE_PATH = "/src/main/java/data/storage.txt";
    public ArrayList<Task> storageTasks = new ArrayList<>();


    public Storage() {
        try {
            ensureFileExists();
            readFile();
        } catch (IOException e) {
            System.out.println("Cannot find or read file!");
        }
    }

    private void ensureFileExists() throws IOException {
        System.out.println("Looking for: " + currentDir + FILE_PATH);
        File storageFile = new File(currentDir + FILE_PATH);
        File directory = storageFile.getParentFile();

        if (storageFile.exists()) { return; }

        // Create ./data/ folder if it doesn't exist
        if (directory.mkdirs()) {
            System.out.println("Directory /data created");
        }
        // Create storage.txt if it doesn't exist
        if (storageFile.createNewFile()) {
            System.out.println("File storage.txt created");
        }
    }

    private void readFile() throws IOException {
        System.out.println("Reading file now");
        BufferedReader reader = new BufferedReader(new FileReader(currentDir + FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                parseTask(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Skipping corrupted line: " + line);
            } catch (TaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void parseTask(String line) throws TaskException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Corrupted line format.");
        }

        String taskType = parts[0].trim().toUpperCase();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (taskType) {
        case "T": // Todo
            storageTasks.add(new Todo(description, isDone));
            break;
        case "D": // Deadline
            if (parts.length < 4) throw new TaskException("Wrong deadline format.");
            String by = parts[3].trim();
            storageTasks.add(new Deadline(description, isDone, by));
            break;
        case "E": // Event
            if (parts.length < 5) throw new TaskException("Wrong event format.");
            String from = parts[3].trim();
            String to = parts[4].trim();
            storageTasks.add(new Event(description, isDone, from, to));
            break;
        default:
            throw new TaskException("Unknown task type.");
        }
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentDir + FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving storageTasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() {
        return storageTasks;
    }
}
