package Parser;

import java.util.HashMap;
import java.util.Set;

import Commands.*;
import Storage.TaskStorage;
import Storage.ToDoList;


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
            "delete"
    );

    private static final HashMap<String, Command> GET_COMMAND= new HashMap<>();

    private void initialize() {
        GET_COMMAND.put("bye", new EndCommand());
        GET_COMMAND.put("list", new ListCommand(toDoList));
        GET_COMMAND.put("mark", new MarkCommand(toDoList,taskStorage));
        GET_COMMAND.put("unmark", new UnmarkCommand(toDoList, taskStorage));
        GET_COMMAND.put("todo", new AddToDoTaskCommand(toDoList, taskStorage));
        GET_COMMAND.put("deadline", new AddDeadlineTaskCommand(toDoList, taskStorage));
        GET_COMMAND.put("event", new AddEventTaskCommand(toDoList, taskStorage));
        GET_COMMAND.put("delete", new DeleteCommand(toDoList, taskStorage));
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
            GET_COMMAND.get(inputCommand).execute(argument.trim());
            return GET_COMMAND.get(inputCommand);
        }
        System.out.println("Pardon me, noble companion, but what dost thou mean to convey?\n");
        return GET_COMMAND.get("list");
    }
}
