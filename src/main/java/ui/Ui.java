package ui;

// Class to store all ascii art
public class Ui {
    // Print greeting message when program is executed
    public static void printGreetings() {
        System.out.println("Welcome to the Galaxy of Cw!");
        System.out.println("How may I help you?");
        printDivider();
    }

    // Print goodbye message when user input "bye"
    public static void printBye() {
        System.out.println("Goodbye! See you soon!");
        Ui.printDivider();
    }

    // Handle empty input
    public static void printEmptyInput() {
        System.out.print("""
            Hmm, I can't store invisible thoughts.
            Try typing something!
            """
        );
    }

    public static void printDivider() {
        String divider = "----------------------------------------------------------------------------";
        System.out.println(divider);
    }
}
