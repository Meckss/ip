import Commands.EndCommand;
import Parser.Parser;
import Storage.TaskStorage;
import Storage.ToDoList;
import Ui.Ui;

import java.io.IOException;
import java.io.File;

public class Hope {
    private final File data;
    private final TaskStorage taskStorage;
    private boolean online;
    private ToDoList toDoList;
    private Ui ui;
    private Parser parser;



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

    public static void main(String[] args) {
        Hope hope = new Hope();
        hope.run();
    }
}
