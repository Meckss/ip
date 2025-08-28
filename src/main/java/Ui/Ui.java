package Ui;

import java.util.HashMap;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    private static HashMap<String, String> MESSAGE_LIST = new HashMap<>();

    static {
        MESSAGE_LIST.put("MESSAGE_WELCOME", "Greetings, fair wanderer!\nI am Hope, your humble companion. How might I" +
                " assist thee on this grand quest?");
        MESSAGE_LIST.put("MESSAGE_INVALID_COMMAND", "Pardon me, noble companion, but what dost thou mean to convey?");
        MESSAGE_LIST.put("MESSAGE_INIT_FAILED", "Error creating file or directory, exitting...");
        MESSAGE_LIST.put("MESSAGE_SHUTDOWN", "Fare thee well, noble friend!");

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
        printToUser(MESSAGE_LIST.get("MESSAGE_INIT_FAILED"));
    }

    public void showMessageWelcome() {
        printToUser(MESSAGE_LIST.get("MESSAGE_WELCOME"));
    }

    public void showShutdownMessage() {
        printToUser(MESSAGE_LIST.get("MESSAGE_SHUTDOWN"));
    }


}
