import Commands.EndCommand;
import Parser.Parser;
import Storage.TaskStorage;
import Storage.ToDoList;
import Ui.Ui;

import java.io.IOException;
import java.io.File;

/**
 * A chatbot application named Hope that manages a to-do list.
 * The {@code Hope} class initializes the chatbot's components, including the user interface,
 * task storage, and parser, and handles user interactions through a command-line interface.
 * It loads tasks from a file, processes user input, and persists tasks to storage.
 * The chatbot runs in a loop until an exit command is received.
 */
public class Hope {
    /** The file where tasks are stored. */
    private final File data;

    /** The storage system for managing task persistence. */
    private final TaskStorage taskStorage;

    /** Indicates whether the chatbot is running. */
    private boolean online;

    /** The to-do list containing the user's tasks. */
    private ToDoList toDoList;

    /** The user interface for interacting with the user. */
    private Ui ui;

    /** The parser for interpreting user input and executing commands. */
    private Parser parser;

    /**
     * Constructs a {@code Hope} chatbot instance.
     * Initializes the user interface, task storage, to-do list, and parser.
     * Creates a data file (Hope.txt) in the ./data directory if it does not exist.
     * If file creation fails, an error message is displayed via the user interface.
     */
    public Hope() {
        ui = new Ui();
        this.data = new File("./data/Hope.txt");
        this.taskStorage = new TaskStorage(data);
        this.online = true;
        this.toDoList = new ToDoList(taskStorage.toList());
        this.parser = new Parser(taskStorage, toDoList);

        try {
            File parentDir = data.getParentFile();
            if(parentDir.exists() && parentDir.isDirectory()) {
                if(!data.exists()) {
                    data.createNewFile();
                }
            } else {
                parentDir.mkdirs();
                data.createNewFile();
            }
        } catch (IOException e) {
            ui.showMessageInitFailed();
        }
    }

    /**
     * Runs the chatbot, starting the main interaction loop.
     * Displays a welcome message, processes user input through the parser,
     * and continues until an {@link EndCommand} is received, at which point
     * a shutdown message is displayed.
     */
    public void run() {
        ui.showMessageWelcome();
        while(online) {
            String userInput = ui.getUserInput();
            if(parser.parse(userInput) instanceof EndCommand) {
                online = false;
            };
        }
        ui.showShutdownMessage();
    }

    /**
     * The main entry point for the Hope chatbot application.
     * Creates a new {@code Hope} instance and starts the chatbot.
     */
    public static void main(String[] args) {
        Hope hope = new Hope();
        hope.run();
    }
}
