package ui;

/**
 * The {@code Ui} class handles user interface messages
 */
public class Ui {
    /**
     * Prints a greeting message when the program starts.
     */
    public static void printGreetings() {
        System.out.println("Welcome to the Galaxy of Cw!");
        System.out.println("How may I help you?");
        printDivider();
    }

    /**
     * Prints a farewell message when the user exits the program.
     */
    public static void printBye() {
        System.out.println("Goodbye! See you soon!");
        Ui.printDivider();
    }

    /**
     * Prints an error message when the user enters an empty command.
     * Suggests the user type something instead of an empty input.
     */
    public static void printEmptyInput() {
        System.out.print("""
            Hmm, I can't store invisible thoughts.
            Try typing something!
            """
        );
    }

    /**
     * Prints a visual divider line to separate messages.
     */
    public static void printDivider() {
        String divider = "----------------------------------------------------------------------------";
        System.out.println(divider);
    }
}
