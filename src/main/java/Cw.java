import art.Art;
import exception.*;
import io.CommandHandler;

import java.util.Scanner;

public class Cw {
    public static void main(String[] args) {
        String name = "Cw";
        Scanner in = new Scanner(System.in);
        CommandHandler.printGreetings(name);

        String line;
        String[] words;

        // While the program has not ended
        do {
            line = in.nextLine().trim();
            words = line.split(" ");
            Art.printDivider();

            // Guard clauses
            if (line.equalsIgnoreCase("bye")) {
                break;
            } else if (line.isBlank()) {
                CommandHandler.printEmptyInput();
                continue;
            }

            boolean isMarkCommand = (words[0].equalsIgnoreCase("mark"))
                    || (words[0].equalsIgnoreCase("unmark"));
            boolean isTaskCommand = words[0].equalsIgnoreCase("todo")
                    || words[0].equalsIgnoreCase("deadline")
                    || words[0].equalsIgnoreCase("event");

            if (line.equalsIgnoreCase("list")) { // Command: "list"
                CommandHandler.printTaskList();
            } else if (isMarkCommand) { // Command: "mark <int>" or "unmark <int>"
                try {
                    CommandHandler.changeStatus(line);
                } catch (MarkException e) {
                    System.out.println(e.getMessage());
                }
            } else if (isTaskCommand) { // Command: "todo" or "deadline" or "event"
                try {
                    CommandHandler.addTask(line);
                } catch (TaskException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Sorry, I don't understand what this means");
            }

            Art.printDivider();
        } while (!line.equalsIgnoreCase("bye"));

        CommandHandler.printBye();
    }
}