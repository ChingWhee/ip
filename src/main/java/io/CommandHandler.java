package io;

import exception.ExitException;
import exception.MarkException;
import exception.TaskException;
import storage.Storage;
import task.Task;
import task.TaskManager;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles user commands by processing command line input and executing corresponding task operations.
 * This class interacts with {@link TaskManager} and {@link Storage} to manage tasks.
 */
public class CommandHandler {
    private TaskManager taskManager;
    private Storage fileStorage;

    /**
     * Constructs a CommandHandler instance to process user commands.
     *
     * @param taskManager The TaskManager instance for managing tasks.
     * @param fileStorage The Storage instance for saving and loading tasks.
     * @throws TaskException If an error occurs during initialization.
     */
    public CommandHandler(TaskManager taskManager, Storage fileStorage) throws TaskException {
        this.taskManager = taskManager;
        this.fileStorage = fileStorage;
    }

    /**
     * Processes user input and executes the corresponding command.
     *
     * @param command The user input command.
     * @throws ExitException If the "bye" command is entered, causing the program to exit.
     */
    public void processCommand(String command) throws ExitException {
        String[] words = command.split(" ");
        Ui.printDivider();

        // Guard clauses
        if (command.equalsIgnoreCase("bye")) {
            throw new ExitException();
        } else if (command.isBlank()) {
            Ui.printEmptyInput();
            return;
        }

        String action = words[0].toUpperCase();
        boolean isMarkCommand = action.equals("MARK") || action.equals("UNMARK");
        boolean isTaskCommand = action.equals("TODO") || action.equals("DEADLINE") || action.equals("EVENT");
        boolean isDelete = action.equals("DELETE");
        boolean isFind = action.equals("FIND");
        boolean isCheck = action.equals("BEFORE") || action.equals("ON") || action.equals("AFTER");

        if (command.equalsIgnoreCase("list")) { // Command: "list"
            printTaskList();
        } else if (isMarkCommand) { // Command: "mark <int>" or "unmark <int>"
            try {
                changeStatus(command);
                fileStorage.saveTasks(taskManager.getTaskList());
            } catch (MarkException e) {
                System.out.println(e.getMessage());
            }
        } else if (isTaskCommand) { // Command: "todo" or "deadline" or "event"
            try {
                addTask(command);
                fileStorage.saveTasks(taskManager.getTaskList());
            } catch (TaskException e) {
                System.out.println(e.getMessage());
            }
        } else if (isDelete) {
            try {
                deleteTask(command);
                fileStorage.saveTasks(taskManager.getTaskList());
            } catch (TaskException e) {
                System.out.println(e.getMessage());
            }
        } else if (isFind) {
            try {
                findTasks(command);
            } catch (TaskException e) {
                System.out.println(e.getMessage());
            }
        } else if (isCheck) {
            try {
                checkDates(command);
            } catch (TaskException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Sorry, I don't understand what this means");
        }

        Ui.printDivider();
    }

    /**
     * Prints the current task list.
     */
    public void printTaskList() {
        taskManager.printTasks();
    }

    /**
     * Throws an exception if a "mark" or "unmark" command is invalid.
     *
     * @param markStatus The type of mark command ("MARK" or "UNMARK").
     * @throws MarkException If the command is invalid.
     */
    private void throwInvalidMarkCommand(String markStatus) throws MarkException {
        switch (markStatus) {
        case "MARK":
            throw new MarkException("Invalid mark command! Use: mark <int>");
        case "UNMARK":
            throw new MarkException("Invalid unmark command! Use: unmark <int>");
        }
    }

    /**
     * Changes the completion status of a task.
     *
     * @param line The command input specifying the task to mark or unmark.
     * @throws MarkException If the input format is invalid.
     */
    public void changeStatus(String line) throws MarkException {
        String[] words = line.split(" ");
        String markStatus = words[0].toUpperCase();

        // Check if the command has the correct number of words
        if (words.length != 2) {
            throwInvalidMarkCommand(markStatus);
        }

        String taskNum = words[1];
        int index = 0;
        try { // Check if the second word can be parsed as an int
            index = Integer.parseInt(taskNum);
        } catch (NumberFormatException e) { // Input is not an integer
            throwInvalidMarkCommand(markStatus);
        }
         // Try to parse the string into an integer
        int tasksCount = taskManager.getTasksCount();
        if (index > tasksCount) { // Check if index is more than index of tasks
            throw new MarkException("You currently only have " + tasksCount + " tasks.");
        } else if (index > 0) {
            if (markStatus.equalsIgnoreCase("mark")) { // Mark as done
                taskManager.changeTaskStatus(true, index);
            } else if (markStatus.equalsIgnoreCase("unmark")) { // Mark as not done
                taskManager.changeTaskStatus(false, index);
            }
        } else { // Input is less than or equal to 0
            throw new MarkException("Negative integer! Please enter a positive integer!");
        }
    }

    /**
     * Adds a new task based on user input (todo, deadline or event)
     *
     * @param line The command input specifying the task type and description.
     * @throws TaskException If the input format is invalid.
     */
    public void addTask(String line) throws TaskException {
        // words[0] is command and words[1] is task
        String[] words = line.split(" ", 2);
        if (words.length < 2) {
            throw new TaskException("Missing task name! Use: <task type> <task name>");
        }

        String command = words[0].toUpperCase();
        switch (command) {
        case "TODO":
            taskManager.addTodo(words[1]);
            break;
        case "DEADLINE":
            taskManager.addDeadline(words[1]);
            break;
        case "EVENT":
            taskManager.addEvent(words[1]);
            break;
        }

        System.out.println("Got it, I have added this task:");
        System.out.println("\t" +  taskManager.getLatestTask());
        System.out.println("Now you have " + taskManager.getTasksCount() + " tasks.");
    }

    /**
     * Deletes a task from the task list.
     *
     * @param line The command input specifying the task index to delete.
     * @throws TaskException If the input format is invalid or the index is out of range.
     */
    public void deleteTask(String line) throws TaskException {
        String[] words = line.split(" ");
        if (words.length != 2) {
            throw new TaskException("Invalid delete command! Use: delete <int>");
        }

        String taskNum = words[1];
        int index;
        try { // Check if the second word can be parsed as an int
            index = Integer.parseInt(taskNum);
        } catch (NumberFormatException e) { // Input is not an integer
            throw new TaskException("Second parameter must be an integer! Use: delete <int>");
        }

        // Try to parse the string into an integer
        int tasksCount = taskManager.getTasksCount();
        if (index > tasksCount) { // Check if index is more than index of tasks
            throw new TaskException("You currently only have " + tasksCount + " tasks.");
        } else if (index > 0) {
            taskManager.deleteTask(index);
        } else { // Input is less than or equal to 0
            throw new TaskException("Negative integer! Please enter a positive integer!");
        }
    }

    /**
     * Finds tasks that match the provided keywords.
     *
     * @param line The command input specifying the search keywords.
     * @throws TaskException If the input format is invalid.
     */
    public void findTasks(String line) throws TaskException {
        String[] words = line.split(" ", 2);
        if (words.length != 2) {
            throw new TaskException("Invalid find command! Use: find <keywords>");
        }

        List<Task> results = taskManager.findTasksByKeywords(words[1]);
        if (results.isEmpty()) {
            System.out.println("There are no matching tasks with the keyword " + "\"" + words[1] + "\"");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i) != null) {
                    System.out.println("\t" + (i + 1) + "." + results.get(i));
                }
            }
        }
    }

    /**
     * Finds tasks occurring before, on, or after a specific date.
     *
     * @param line The command input specifying the date criteria.
     * @throws TaskException If the input format is invalid.
     */
    public void checkDates(String line) throws TaskException {
        String[] words = line.split(" ");
        String action = words[0].toLowerCase();
        if (words.length != 2) {
            throw new TaskException("Invalid date format! Use: dd/MM/yyyy (e.g., 15/10/2025).");
        }

        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(words[1], DATE_FORMAT);
            List<Task> results = (action.equals("before")) ? taskManager.findTasksBeforeDate(date) :
                    (action.equals("after")) ? taskManager.findTasksAfterDate(date) :
                            taskManager.findTasksOnDate(date);

            if (results.isEmpty()) {
                System.out.println("No tasks found to be done " + action + " " + words[1]);
            } else {
                System.out.println("Tasks " + action + " " + words[1] + ":");
                for (int i = 0; i < results.size(); i++) {
                    if (results.get(i) != null) {
                        System.out.println("\t" + (i + 1) + "." + results.get(i));
                    }
                }
            }
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format! Use: dd/MM/yyyy (e.g., 15/10/2025).");
        }
    }
}