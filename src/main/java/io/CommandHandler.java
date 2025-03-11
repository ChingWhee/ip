package io;

import exception.ExitException;
import exception.MarkException;
import exception.TaskException;
import storage.Storage;
import task.Task;
import task.TaskList;
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
    private final TaskManager taskManager;
    private final Storage fileStorage;

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

        switch (action) {
        case "LIST":
            printTaskList();
            break;

        case "MARK":
        case "UNMARK":
            handleMarkCommand(command);
            break;

        case "TODO":
        case "DEADLINE":
        case "EVENT":
            handleTaskCommand(command);
            break;

        case "DELETE":
            handleDeleteCommand(command);
            break;

        case "FIND":
            handleFindCommand(command);
            break;

        case "BEFORE":
        case "ON":
        case "AFTER":
            handleCheckDateCommand(command);
            break;

        default:
            System.out.println("Sorry, I don't understand what this means.");
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
     * Handles the "mark" or "unmark" command.
     *
     * @param command The user input command.
     */
    private void handleMarkCommand(String command) {
        try {
            changeStatus(command);
            fileStorage.saveTasks(taskManager.getTaskList());
        } catch (MarkException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the addition of tasks.
     *
     * @param command The user input command.
     */
    private void handleTaskCommand(String command) {
        try {
            addTask(command);
            fileStorage.saveTasks(taskManager.getTaskList());
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the deletion of tasks.
     *
     * @param command The user input command.
     */
    private void handleDeleteCommand(String command) {
        try {
            deleteTask(command);
            fileStorage.saveTasks(taskManager.getTaskList());
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles finding tasks.
     *
     * @param command The user input command.
     */
    private void handleFindCommand(String command) {
        try {
            findTasks(command);
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles checking tasks before, after, or on a specific date.
     *
     * @param command The user input command.
     */
    private void handleCheckDateCommand(String command) {
        try {
            checkDates(command);
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
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

        int index = 0;
        try {
            index = Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throwInvalidMarkCommand(markStatus);
        }

        int tasksCount = taskManager.getTasksCount();
        if (index > tasksCount) {
            throw new MarkException("You currently only have " + tasksCount + " tasks.");
        } else if (index < 1) { // Input is less than or equal to 0
            throw new MarkException("Value less than 1! Please enter a positive integer!");
        }

        taskManager.changeTaskStatus(markStatus.equals("MARK"), index);
    }

    /**
     * Adds a new task based on user input (todo, deadline, or event).
     *
     * @param line The command input specifying the task type and description.
     * @throws TaskException If the input format is invalid.
     */
    public void addTask(String line) throws TaskException {
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

        int index;
        try {
            index = Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task number! Use: delete <int>");
        }

        int tasksCount = taskManager.getTasksCount();
        if (index > tasksCount) {
            throw new TaskException("You currently only have " + tasksCount + " tasks.");
        } else if (index < 1) { // Input is less than or equal to 0
            throw new TaskException("Value less than 1! Please enter a positive integer!");
        }

        taskManager.deleteTask(index);
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
        TaskList matchingTasks = new TaskList();
        for (Task task : results) {
            matchingTasks.addTask(task);
        }
        Ui.printMatchingTasks(matchingTasks, words[1]);
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
            List<Task> results;

            if (action.equals("before")) {
                results = taskManager.findTasksBeforeDate(date);
            } else if (action.equals("after")) {
                results = taskManager.findTasksAfterDate(date);
            } else {
                results = taskManager.findTasksOnDate(date);
            }

            TaskList tasksByDate = new TaskList();
            for (Task task : results) {
                tasksByDate.addTask(task);
            }
            Ui.printTasksByDate(tasksByDate, action + " " + words[1]);
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format! Use: dd/MM/yyyy (e.g., 15/10/2025).");
        }
    }
}
