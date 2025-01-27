import java.util.Scanner;

public class Cw {
    public static void main(String[] args) {
        String name = "Cw";
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int tasksCount = 0;

        System.out.println("Welcome to the Galaxy of " + name + "!");
        Art.printGalaxy();
        System.out.println("How may I help you?");
        Art.printDivider();

        String line = in.nextLine().trim();
        String[] words = line.split(" ");
        Art.printDivider();
        while (!line.equalsIgnoreCase("bye")) {
            if (line.isBlank()) {
                // If user did not type any text, let them know
                System.out.print("""
                    Hmm, I can't store invisible thoughts.
                    Try typing something!
                    """);
            } else if (line.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                // List all user input
                for (int i = 0; i < tasksCount; i++) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
            } else if (words[0].equalsIgnoreCase("mark") && words.length > 1) {
                // Mark task as done
                try {
                    int number = Integer.parseInt(words[1]); // Try to parse the string into an integer
                    if (number > tasksCount) {
                        System.out.println("You currently only have " + tasksCount + " tasks.");
                    } else if (number > 0) {
                        System.out.println("Nice! I've marked this task as done:");
                        tasks[number - 1].setStatus(true);
                        System.out.println("\t[" + tasks[number - 1].getStatusIcon() + "] "
                                + tasks[number - 1].getDescription());
                    } else {
                        System.out.println("Invalid input! Please enter a positive number!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a positive integer.");
                }
            } else if (words[0].equalsIgnoreCase("unmark") && words.length > 1) {
                // Mark task as undone
                try {
                    int number = Integer.parseInt(words[1]); // Try to parse the string into an integer
                    if (number > tasksCount) {
                        System.out.println("You currently only have " + tasksCount + " tasks.");
                    } else if (number > 0) {
                        System.out.println("OK, I've marked this task as not done yet:");
                        tasks[number - 1].setStatus(false);
                        System.out.println("\t[" + tasks[number - 1].getStatusIcon() + "] "
                                + tasks[number - 1].getDescription());
                    } else {
                        System.out.println("Invalid input! Please enter a positive number!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a positive integer.");
                }
            } else {
                // Echo user input
                System.out.println("added: " + line);
                tasks[tasksCount] = new Task(line);
                tasksCount++;
            }
            Art.printDivider();
            line = in.nextLine().trim();
            words = line.split(" ");
            Art.printDivider();
        }
        System.out.println("Goodbye! See you soon!");
        Art.printDivider();
    }
}