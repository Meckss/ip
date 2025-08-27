import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

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
        this.parser = new Parser(taskStorage, toDoList, this);

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
            parser.parse(userInput);
        }
        ui.showShutdownMessage();
    }

    public void shutdown() {
        this.online = false;
    }

    public static void main(String[] args) {
        Hope hope = new Hope();
        hope.run();
    }
}
