package storage;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import exception.TaskException;
import task.*;

/**
 * Handles file storage operations for tasks.
 * This class ensures tasks are saved to and loaded from a file.
 */
public class Storage {
    private final String currentDir = System.getProperty("user.dir");
    private final String filePath;
    public ArrayList<Task> storageTasks = new ArrayList<>();

    /**
     * Constructs a Storage instance, ensuring the storage file exists and read tasks from it.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            ensureFileExists();
            readFile();
        } catch (IOException e) {
            System.out.println("Cannot find or read file!");
        }
    }

    /**
     * Ensures that the storage file and its parent directory exist.
     * If they do not exist, this method creates them.
     *
     * @throws IOException If an error occurs while creating the file or directory.
     */
    private void ensureFileExists() throws IOException {
        File storageFile = new File(currentDir + filePath);
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

    /**
     * Reads the task storage file and loads tasks into memory.
     * If a line is corrupted, it is skipped.
     *
     * @throws IOException If an error occurs while reading the file.
     */
    private void readFile() throws IOException {
//        System.out.println("Reading file now");
        BufferedReader reader = new BufferedReader(new FileReader(currentDir + filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                parseTask(line);
            } catch (IllegalArgumentException | DateTimeParseException | TaskException e) {
                System.out.println("Skipping corrupted line: " + line);
            }
        }
    }

    /**
     * Parses a line from the storage file and converts it into a task.
     *
     * @param line The line to parse.
     * @throws TaskException If the line format is incorrect or contains an unknown task type.
     */
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

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentDir + filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving storageTasks: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks stored in memory.
     *
     * @return The list of loaded tasks.
     */
    public ArrayList<Task> loadTasks() {
        return storageTasks;
    }
}
