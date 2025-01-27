import java.util.Scanner;

public class Cw {
    public static void main(String[] args) {
        String name = "Cw";
        Scanner in = new Scanner(System.in);
        Controller.printGreetings(name);

        String line = in.nextLine().trim();
        String[] words = line.split(" ");
        Art.printDivider();
        while (!line.equalsIgnoreCase("bye")) {
            if (line.isBlank()) {
                Controller.printEmptyInput();
            } else if (line.equalsIgnoreCase("list")) {
                Controller.printTaskList();
            } else if ((words[0].equalsIgnoreCase("mark")) || (words[0].equalsIgnoreCase("unmark"))
                    && words.length == 2) {
                Controller.printChangeStatus(words[0], words[1]);
            } else {
                Controller.printEcho(line);
            }
            Art.printDivider();
            line = in.nextLine().trim();
            words = line.split(" ");
            Art.printDivider();
        }
        Controller.printBye();
    }
}