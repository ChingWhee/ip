package io;

import exception.MarkException;
import exception.TaskException;
import task.TaskManager;
import art.Art;

public class CommandHandler {
    // Print greeting message when program is executed
    public static void printGreetings(String name) {
        System.out.println("Welcome to the Galaxy of " + name + "!");
//        Art.printGalaxy();
        System.out.println("How may I help you?");
        Art.printDivider();
    }

    // Print goodbye message when user input "bye"
    public static void printBye() {
        System.out.println("Goodbye! See you soon!");
        Art.printDivider();
    }

    // Handle empty input
    public static void printEmptyInput() {
        System.out.print("""
            Hmm, I can't store invisible thoughts.
            Try typing something!
            """
        );
    }

    // Handle "list" command
    public static void printTaskList() {
        TaskManager.printTasks();
    }

    private static void throwInvalidMarkCommand(String markStatus) throws MarkException {
        switch (markStatus) {
        case "MARK":
            throw new MarkException("Invalid command! Use: mark <int>");
        case "UNMARK":
            throw new MarkException("Invalid command! Use: unmark <int>");
        }
    }
    // Handle "mark" or "unmark" command
    public static void changeStatus(String line) throws MarkException {
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
        int tasksCount = TaskManager.getTasksCount();
        if (index > tasksCount) { // Check if index is more than index of tasks
            throw new MarkException("You currently only have " + tasksCount + " tasks.");
        } else if (index > 0) {
            if (markStatus.equalsIgnoreCase("mark")) { // Mark as done
                TaskManager.changeTaskStatus(true, index);
            } else if (markStatus.equalsIgnoreCase("unmark")) { // Mark as not done
                TaskManager.changeTaskStatus(false, index);
            }
        } else { // Input is less than or equal to 0
            throw new MarkException("Negative integer! Please enter a positive integer!");
        }

    }

    // Echo user input
    public static void printEcho(String line) {
        System.out.println(line);

    }

    // Handle "todo", "deadline" or "event" command
    public static void addTask(String line) throws TaskException {
        // 0 is command and 1 is task
        String[] words = line.split(" ", 2);
        if (words.length < 2) {
            throw new TaskException("Missing task name! Use: <task type> <task name>");
        }

        String command = words[0].toUpperCase();
        switch (command) {
        case "TODO":
            TaskManager.addTodo(words[1]);
            break;
        case "DEADLINE":
            TaskManager.addDeadline(words[1]);
            break;
        case "EVENT":
            TaskManager.addEvent(words[1]);
            break;
        }

        System.out.println("Got it, I have added this task:");
        System.out.println("\t" +  TaskManager.getLatestTask());
        System.out.println("Now you have " + TaskManager.getTasksCount() + " tasks.");
    }
}
