import art.Art;
import exception.TaskException;
import io.CommandHandler;

import java.io.Serial;
import java.util.MissingFormatArgumentException;
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
            } else if (words.length == 2 && isMarkCommand) { // Command: "mark <int>" or "unmark <int>"
                CommandHandler.printChangeStatus(words[0], words[1]);
            } else if (isTaskCommand) { // Command: "todo" or "deadline" or "event"
                try {
                    CommandHandler.addTask(line);
                } catch (TaskException e) {
                    System.out.println("Missing task name");
                }
            } else {
                System.out.println("Sorry, I don't understand what this means");
            }
            Art.printDivider();
        } while (!line.equalsIgnoreCase("bye"));

        CommandHandler.printBye();
    }
}