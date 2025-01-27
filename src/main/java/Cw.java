import java.util.Scanner;

public class Cw {
    public static void main(String[] args) {
        String name = "Cw";
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int tasksCount = 0;

        System.out.println("Welcome to the Galaxy of " + name + "!");
        Art.printGalaxy();
        System.out.println("How may I help you?");
        Art.printDivider();

        String line = in.nextLine();
        Art.printDivider();
        while (!line.equalsIgnoreCase("bye")) {
            if (line.isBlank()) {
                // If user did not type any text, let them know
                System.out.print("""
                    Hmm, I can't store invisible thoughts.
                    Try typing something!
                    """);
            } else if (line.equalsIgnoreCase("list")) {
                // List all user input
                for (int i = 0; i < tasksCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                // Echo user input
                System.out.println("added: " + line);
                tasks[tasksCount] = line;
                tasksCount++;
            }
            Art.printDivider();
            line = in.nextLine();
            Art.printDivider();
        }
        System.out.println("Goodbye! See you soon!");
        Art.printDivider();
    }
}

