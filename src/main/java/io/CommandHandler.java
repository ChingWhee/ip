package io;

import task.TaskManager;
import art.Art;

import java.util.MissingFormatArgumentException;

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

    // Handle "mark" or "unmark" command
    public static void printChangeStatus(String markStatus, String taskNum) {
        try {
            int index = Integer.parseInt(taskNum); // Try to parse the string into an integer
            int tasksCount = TaskManager.getTasksCount();
            if (index > tasksCount) { // Check if index is more than index of tasks
                System.out.println("You currently only have " + tasksCount + " tasks.");
            } else if (index > 0) {
                if (markStatus.equalsIgnoreCase("mark")) { // Mark as done
                    TaskManager.changeTaskStatus(true, index);
                } else if (markStatus.equalsIgnoreCase("unmark")) { // Mark as not done
                    TaskManager.changeTaskStatus(false, index);
                }
            } else { // Input is less than or equal to 0
                System.out.println("Negative integer! Please enter a positive index!");
            }
        } catch (NumberFormatException e) { // Input is not an integer
            System.out.println("Invalid index! Please enter a positive integer.");
        }
    }

    // Echo user input
    public static void printEcho(String line) {
        System.out.println(line);

    }

    // Handle "todo", "deadline" or "event" command
    public static void addTask(String line) {
        // 0 is command and 1 is task
        String[] words = line.split(" ", 2);
        try {
            String command = words[0].toUpperCase();
            switch (command) {
            case "TODO": TaskManager.addTodo(words[1]);
                break;
            case "DEADLINE": TaskManager.addDeadline(words[1]);
                break;
            case "EVENT": TaskManager.addEvent(words[1]);
                break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Got it, I have added this task:");
        System.out.println("\t" +  TaskManager.getLatestTask());
        System.out.println("Now you have " + TaskManager.getTasksCount() + " tasks.");
    }
}
