package Ui;

import java.util.HashMap;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    private static HashMap<String, String> MESSAGELIST = new HashMap<>();

    static {
        MESSAGELIST.put("MESSAGE_WELCOME", "Greetings, fair wanderer!\nI am Hope, your humble companion. How might I assist thee on this grand quest?");
        MESSAGELIST.put("MESSAGE_INVALID_COMMAND", "Pardon me, noble companion, but what dost thou mean to convey?");
        MESSAGELIST.put("MESSAGE_INIT_FAILED", "Error creating file or directory, exitting...");
        MESSAGELIST.put("MESSAGE_SHUTDOWN", "Fare thee well, noble friend!");

    }

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void printToUser(String msg) {
        System.out.println(msg + "\n");
    }

    public void showMessageInitFailed() {
        printToUser(MESSAGELIST.get("MESSAGE_INIT_FAILED"));
    }

    public void showMessageWelcome() {
        printToUser(MESSAGELIST.get("MESSAGE_WELCOME"));
    }

    public void showShutdownMessage() {
        printToUser(MESSAGELIST.get("MESSAGE_SHUTDOWN"));
    }


}
