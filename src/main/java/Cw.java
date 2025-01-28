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
        while (!line.equalsIgnoreCase("bye")) {
            if (line.isBlank()) {
                CommandHandler.printEmptyInput();
            } else if (line.equalsIgnoreCase("list")) {
                CommandHandler.printTaskList();
            } else if ((words[0].equalsIgnoreCase("mark"))
                    || (words[0].equalsIgnoreCase("unmark"))
                    && words.length == 2) {
                CommandHandler.printChangeStatus(words[0], words[1]);
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