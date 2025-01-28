package io;

import task.TaskManager;
import art.Art;

public class CommandHandler {
    public static void printGreetings(String name) {
        System.out.println("Welcome to the Galaxy of " + name + "!");
        Art.printGalaxy();
        System.out.println("How may I help you?");
        Art.printDivider();
    }

    public static void printBye() {
        System.out.println("Goodbye! See you soon!");
        Art.printDivider();
    }

    public static void printEmptyInput() {
        // If user did not type any text, let them know
        System.out.print("""
            Hmm, I can't store invisible thoughts.
            Try typing something!
            """);
    }

    public static void printTaskList() {
        // List all tasks
        System.out.println("Here are the tasks in your list:");
        TaskManager.printTasks();
    }

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
            System.out.println("Invalid number! Please enter a positive integer.");
        }
    }

    public static void printEcho(String line) {
        // Echo user input
        System.out.println("added: " + line);
        TaskManager.addTask(line);
    }
}
