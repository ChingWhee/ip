package io;

import exception.ExitException;
import exception.MarkException;
import exception.TaskException;
import storage.Storage;
import task.TaskManager;
import ui.Ui;

public class CommandHandler {
    private TaskManager taskManager;
    private Storage fileStorage;

    public CommandHandler(TaskManager taskManager, Storage fileStorage) throws TaskException {
        this.taskManager = taskManager;
        this.fileStorage = fileStorage;
    }

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

        boolean isMarkCommand = (words[0].equalsIgnoreCase("mark"))
                || (words[0].equalsIgnoreCase("unmark"));
        boolean isTaskCommand = words[0].equalsIgnoreCase("todo")
                || words[0].equalsIgnoreCase("deadline")
                || words[0].equalsIgnoreCase("event");
        boolean isDelete = words[0].equalsIgnoreCase("delete");

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
        } else {
            System.out.println("Sorry, I don't understand what this means");
        }

        Ui.printDivider();
    }

    // Handle "list" command
    public void printTaskList() {
        taskManager.printTasks();
    }

    private void throwInvalidMarkCommand(String markStatus) throws MarkException {
        switch (markStatus) {
        case "MARK":
            throw new MarkException("Invalid mark command! Use: mark <int>");
        case "UNMARK":
            throw new MarkException("Invalid unmark command! Use: unmark <int>");
        }
    }
    // Handle "mark" or "unmark" command
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

    // Handle "todo", "deadline" or "event" command
    public void addTask(String line) throws TaskException {
        // 0 is command and 1 is task
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

    // Delete tasks
    public void deleteTask(String line) throws TaskException {
        String[] words = line.split(" ");
        if (words.length != 2) {
            throw new TaskException("Invalid delete command! Use: delete <int>");
        }

        String taskNum = words[1];
        int index = 0;
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
}