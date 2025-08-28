package Parser;

import Commands.*;
import Storage.TaskStorage;
import Storage.ToDoList;

import java.util.HashMap;
import java.util.Set;

public class Parser {
    private ToDoList toDoList;
    private TaskStorage taskStorage;

    private static final Set<String> COMMANDS = Set.of(
            "bye",
            "list",
            "mark",
            "unmark",
            "todo",
            "deadline",
            "event",
            "delete",
            "find"
    );

    private static final HashMap<String, Command> EXECUTECOMMAND= new HashMap<>();

    private void initialize() {
        EXECUTECOMMAND.put("bye", new EndCommand());
        EXECUTECOMMAND.put("list", new ListCommand(toDoList));
        EXECUTECOMMAND.put("mark", new MarkCommand(toDoList,taskStorage));
        EXECUTECOMMAND.put("unmark", new UnmarkCommand(toDoList, taskStorage));
        EXECUTECOMMAND.put("todo", new AddToDoTaskCommand(toDoList, taskStorage));
        EXECUTECOMMAND.put("deadline", new AddDeadlineTaskCommand(toDoList, taskStorage));
        EXECUTECOMMAND.put("event", new AddEventTaskCommand(toDoList, taskStorage));
        EXECUTECOMMAND.put("delete", new DeleteCommand(toDoList, taskStorage));
        EXECUTECOMMAND.put("find", new FindCommand(toDoList));
    }

    public Parser(TaskStorage taskStorage, ToDoList toDoList) {
        this.taskStorage = taskStorage;
        this.toDoList = toDoList;
        this.initialize();
    }

    public Command parse(String input) {
        String[] instructions = input.split(" ", 2);
        String inputCommand = instructions[0];
        String argument = instructions.length == 2 ? instructions[1] : input;
        if(COMMANDS.contains(inputCommand)) {
            EXECUTECOMMAND.get(inputCommand).execute(argument.trim());
            return EXECUTECOMMAND.get(inputCommand);
        }
        System.out.println("Pardon me, noble companion, but what dost thou mean to convey?\n");
        return EXECUTECOMMAND.get("list");
    }
}
