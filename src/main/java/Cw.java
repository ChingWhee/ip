import task.TaskList;
import ui.Ui;
import exception.*;
import io.CommandHandler;
import java.util.Scanner;

import storage.Storage;
import task.TaskManager;

/**
 * The main entry point for the task management application.
 * This class initializes the necessary components such as storage, task management, and command handling.
 * It reads the tasks stored in the defined storage text file
 * It also provides the main event loop to process user commands.
 */
public class Cw {
    private static CommandHandler commandHandler;

    /**
     * The main method that starts the application.
     * It initializes UI, loads existing tasks from storage, and sets up command handling.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Ui.printGreetings();

        // Read existing tasks from storage file
        Storage fileStorage = new Storage();
        TaskList taskList = new TaskList();
        taskList.load(fileStorage);

        try {
            TaskManager taskManager = new TaskManager(taskList);
            commandHandler = new CommandHandler(taskManager, fileStorage);
        } catch (TaskException e) {
            System.out.println("Cannot create task list!");
            return;
        }

        // Start reading from command line
        run();
    }

    /**
     * Runs the command loop, continuously reading and executing code according to command line input
     * The loop continues until the user enters an exit command or type "bye"
     */
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