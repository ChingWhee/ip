package ui;

import task.Task;
import task.TaskList;

/**
 * The {@code Ui} class handles all user interface interactions,
 * printing messages and formatting output for the user.
 */
public class Ui {

    /**
     * Prints a greeting message when the program starts.
     */
    public static void printGreetings() {
        System.out.println("Welcome to the Galaxy of Cw!");
        System.out.println("How may I help you?");
        printDivider();
    }

    /**
     * Prints a farewell message when the user exits the program.
     */
    public static void printBye() {
        System.out.println("Goodbye! See you soon!");
        printDivider();
    }

    /**
     * Prints an error message when the user enters an empty command.
     * Suggests the user type something instead of an empty input.
     */
    public static void printEmptyInput() {
        System.out.println("Hmm, I can't store invisible thoughts.");
        System.out.println("Try typing something!");
    }

    /**
     * Prints an error message when the task list cannot be created.
     */
    public static void printTaskListError() {
        System.out.println("Cannot create task list!");
    }

    /**
     * Prints a message when a task is marked as done.
     */
    public static void printMarkAsDone() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Prints a message when a task is marked as not done.
     */
    public static void printMarkAsNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Prints a visual divider line to separate messages.
     */
    public static void printDivider() {
        System.out.println("----------------------------------------------------------------------------");
    }

    /**
     * Prints all tasks in the task list.
     * If no tasks are available, it notifies the user.
     *
     * @param taskList The TaskList instance containing tasks to be displayed.
     */
    public static void printTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks yet! Start adding some tasks");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i));
        }
    }

    /**
     * Prints the task at the specified index.
     * Used for confirming changes to task status.
     *
     * @param taskList The list of tasks.
     * @param index The index of the task to print.
     */
    public static void printLatestTask(TaskList taskList, int index) {
        System.out.println("\t" + taskList.getTask(index - 1));
    }

    /**
     * Prints a message indicating that a new task has been added to the task list.
     * Displays the task details and the updated number of remaining tasks.
     *
     * @param task The newly added task.
     * @param remainingTasks The total number of tasks after adding the new task.
     */
    public static void printNewTask(Task task, int remainingTasks) {
        System.out.println("Got it, I have added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + remainingTasks + " tasks.");
    }

    /**
     * Prints a task that has been deleted along with the number of remaining tasks.
     *
     * @param task The task that was deleted.
     * @param remainingTasks The number of tasks left in the task list.
     */
    public static void printDeletedTask(Task task, int remainingTasks) {
        System.out.println("Alright, I have deleted this task!");
        System.out.println("\t" + task);
        System.out.println("Now you have " + remainingTasks + " tasks left.");
    }

    /**
     * Prints a message when no matching tasks are found for a search query.
     *
     * @param keyword The search keyword that did not match any tasks.
     */
    public static void printNoMatchingTasks(String keyword) {
        System.out.println("There are no matching tasks with the keyword \"" + keyword + "\".");
    }

    /**
     * Prints a list of tasks that match a given search query.
     *
     * @param taskList The list of matching tasks.
     */
    public static void printMatchingTasks(TaskList taskList, String keyword) {
        if (taskList.isEmpty()) {
            System.out.println("There are no matching tasks with the keyword " + "\"" + keyword + "\"");
            return;
        }

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.getTask(i));
        }
    }

    /**
     * Prints the result of checking tasks before, after, or on a specific date.
     *
     * @param taskList The list of tasks matching the date criteria.
     * @param dateDescription The formatted date condition (e.g., "before 15/10/2025").
     */
    public static void printTasksByDate(TaskList taskList, String dateDescription) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks found " + dateDescription + ".");
            return;
        }

        System.out.println("Tasks " + dateDescription + ":");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.getTask(i));
        }
    }
}
