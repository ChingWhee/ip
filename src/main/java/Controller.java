public class Controller {
    public static Task[] tasks = new Task[100];
    public static int tasksCount = 0;

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
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }

    public static void printChangeStatus(String markStatus, String taskNum) {
        try {
            int number = Integer.parseInt(taskNum); // Try to parse the string into an integer
            if (number > tasksCount) { // Check if number is more than number of tasks
                System.out.println("You currently only have " + tasksCount + " tasks.");
            } else if (number > 0) {
                if (markStatus.equalsIgnoreCase("mark")) { // Mark as done
                    System.out.println("Nice! I've marked this task as done:");
                    tasks[number - 1].setStatus(true);
                    System.out.println("\t[" + tasks[number - 1].getStatusIcon() + "] "
                            + tasks[number - 1].getDescription());
                } else if (markStatus.equalsIgnoreCase("unmark")) { // Mark as not done
                    System.out.println("OK, I've marked this task as not done yet:");
                    tasks[number - 1].setStatus(false);
                    System.out.println("\t[" + tasks[number - 1].getStatusIcon() + "] "
                            + tasks[number - 1].getDescription());
                }
            } else { // Input is less than or equal to 0
                System.out.println("Negative integer! Please enter a positive number!");
            }
        } catch (NumberFormatException e) { // Input is not an integer
            System.out.println("Invalid number! Please enter a positive integer.");
        }
    }

    public static void printEcho(String line) {
        // Echo user input
        System.out.println("added: " + line);
        tasks[tasksCount] = new Task(line);
        tasksCount++;
    }
}
