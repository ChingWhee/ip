package storage;

import java.io.*;
import java.util.ArrayList;
import exception.TaskException;
import task.*;

public class Storage {
    private static final String FILE_PATH = "src/main/java/data/storage.txt";
    private static final File storageFile = new File(FILE_PATH);

    public Storage() throws IOException {
        System.out.println("Looking for: " + FILE_PATH);
        ensureFileExists();
        System.out.println("Reading file now");
        readFile();
    }

    private void ensureFileExists() throws IOException {
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
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                TaskManager.parseTask(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Skipping corrupted line: " + line);
            } catch (TaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
