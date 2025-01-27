import java.util.Scanner;

public class Cw {
    public static void main(String[] args) {
        String name = "Cw";
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the Galaxy of " + name + "!");
        Art.printGalaxy();
        System.out.println("How may I help you?");
        Art.printDivider();

        String line = in.nextLine();
        Art.printDivider();
        while (!line.equalsIgnoreCase("bye")) {
            System.out.println(line);
            Art.printDivider();
            line = in.nextLine();
            Art.printDivider();
        }
        System.out.println("Goodbye! See you soon!");
        Art.printDivider();
    }
}

