import task.TaskList;
import ui.Ui;
import exception.*;
import io.CommandHandler;
import java.util.Scanner;

import storage.Storage;
import task.TaskManager;

public class Cw {
    private static TaskManager taskManager;
    private static CommandHandler commandHandler;

    public static void main(String[] args) {
        Ui.printGreetings();

        // Read existing files from storage file
        Storage fileStorage = new Storage();
        TaskList taskList = new TaskList();
        taskList.load(fileStorage);

        try {
            taskManager = new TaskManager(taskList);
            commandHandler = new CommandHandler(taskManager, fileStorage, taskList);
        } catch (TaskException e) {
            System.out.println("Cannot create task list!");
            return;
        }

        // Start reading from command line
        run();
    }

    private static void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                commandHandler.processCommand(in.nextLine().trim());
            } catch (ExitException e) {
                Ui.printBye();
                return;
            }
        }
    }
}