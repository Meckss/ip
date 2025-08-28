package Ui;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A class responsible for handling user interface operations, including reading user input.
 * It uses a Scanner for input and maintains a collection of predefined messages.
 */
public class Ui {

    /** The Scanner object used to read user input from the console. */
    private final Scanner sc;

    /** A HashMap storing predefined message strings, mapped by their keys. */
    private static HashMap<String, String> MESSAGE_LIST = new HashMap<>();

    /**
     * Static initialization block to populate the MESSAGELIST with predefined messages.
     */
    static {
        MESSAGE_LIST.put("MESSAGE_WELCOME", "Greetings, fair wanderer!\nI am Hope, your humble companion. How might I" +
                " assist thee on this grand quest?");
        MESSAGE_LIST.put("MESSAGE_INVALID_COMMAND", "Pardon me, noble companion, but what dost thou mean to convey?");
        MESSAGE_LIST.put("MESSAGE_INIT_FAILED", "Error creating file or directory, exitting...");
        MESSAGE_LIST.put("MESSAGE_SHUTDOWN", "Fare thee well, noble friend!");

    }

    /**
     * Constructs a Ui object, initializing the Scanner for reading user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads and returns a line of user input from the console.
     *
     * @return the user input as a String
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints the specified message to the console, followed by a newline.
     *
     * @param msg the message to be printed
     */
    public void printToUser(String msg) {
        System.out.println(msg + "\n");
    }

    /**
     * Displays the initialization failure message to the user.
     */
    public void showMessageInitFailed() {
        printToUser(MESSAGE_LIST.get("MESSAGE_INIT_FAILED"));
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showMessageWelcome() {
        printToUser(MESSAGE_LIST.get("MESSAGE_WELCOME"));
    }

    /**
     * Displays the shutdown message to the user.
     */
    public void showShutdownMessage() {
        printToUser(MESSAGE_LIST.get("MESSAGE_SHUTDOWN"));
    }


}
