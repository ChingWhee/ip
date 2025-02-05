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
            if (line.isBlank()) {
                CommandHandler.printEmptyInput();
            } else if (line.equalsIgnoreCase("list")) { // Command: "list"
                CommandHandler.printTaskList();
            } else if ((words[0].equalsIgnoreCase("mark")) // Command: "mark <int>" or "unmark <int>"
                    || (words[0].equalsIgnoreCase("unmark"))
                    && words.length == 2) {
                CommandHandler.printChangeStatus(words[0], words[1]);
            } else if ( words.length >= 2 && ((words[0].equalsIgnoreCase("todo")) // Command: "mark <int>" or "unmark <int>"
                    || (words[0].equalsIgnoreCase("deadline"))
                    || (words[0].equalsIgnoreCase("event")))) {
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