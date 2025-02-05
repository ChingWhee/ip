import art.Art;
import io.CommandHandler;

import java.util.Scanner;

public class Cw {
    public static void main(String[] args) {
        String name = "Cw";
        Scanner in = new Scanner(System.in);
        CommandHandler.printGreetings(name);

        String line = in.nextLine().trim();
        String[] words = line.split(" ");
        Art.printDivider();
        // While the program has not ended
        while (!line.equalsIgnoreCase("bye")) {
            boolean isMarkCommand = (words[0].equalsIgnoreCase("mark"))
                    || (words[0].equalsIgnoreCase("unmark"));
            boolean isTaskCommand = words[0].equalsIgnoreCase("todo")
                    || words[0].equalsIgnoreCase("deadline")
                    || words[0].equalsIgnoreCase("event");

            if (line.isBlank()) {
                CommandHandler.printEmptyInput();
            } else if (line.equalsIgnoreCase("list")) { // Command: "list"
                CommandHandler.printTaskList();
            } else if (words.length == 2 && isMarkCommand) { // Command: "mark <int>" or "unmark <int>"
                CommandHandler.printChangeStatus(words[0], words[1]);
            } else if (words.length >= 2 && isTaskCommand) { // Command: "todo" or "deadline" or "event"
                CommandHandler.addTask(line);
            } else {
                CommandHandler.printEcho(line);
            }
            Art.printDivider();
            line = in.nextLine().trim();
            words = line.split(" ");
            Art.printDivider();
        }
        CommandHandler.printBye();
    }
}