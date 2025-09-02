package parser;

import java.util.HashMap;
import java.util.Set;

import commands.AddDeadlineTaskCommand;
import commands.AddEventTaskCommand;
import commands.AddToDoTaskCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.EndCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import storage.TaskStorage;
import storage.ToDoList;


/**
 * A parser that interprets user input and dispatches corresponding commands for the Hope chatbot.
 * The {@code Parser} class processes input strings, identifies valid commands, and executes them
 * using the appropriate {@link Command} implementation. It maintains a set of supported commands
 * and a mapping of commands to their respective {@link Command} objects. If an invalid command
 * is provided, an error message is displayed, and the list command is executed by default.
 */
public class Parser {

    /** A set of valid command names recognized by the parser. */
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

    /** A mapping of command names to their corresponding {@link Command} implementations. */
    private static HashMap<String, Command> GET_COMMAND = new HashMap<>();

    /** The to-do list used by commands to manage tasks. */
    private ToDoList toDoList;

    /** The storage system for persisting tasks. */
    private TaskStorage taskStorage;

    /**
     * Constructs a {@code Parser} with the specified task storage and to-do list.
     * Initializes the command mapping by calling {@link #initialize()}.
     *
     * @param taskStorage the {@link TaskStorage} for persisting tasks
     * @param toDoList    the {@link ToDoList} for managing tasks
     */
    public Parser(TaskStorage taskStorage, ToDoList toDoList) {
        this.taskStorage = taskStorage;
        this.toDoList = toDoList;
        this.initialize();
    }

    /**
     * Initializes the command mapping by associating command names with their respective
     * {@link Command} implementations.
     */
    private void initialize() {
        GET_COMMAND.put("bye", new EndCommand());
        GET_COMMAND.put("list", new ListCommand(toDoList));
        GET_COMMAND.put("mark", new MarkCommand(toDoList, taskStorage));
        GET_COMMAND.put("unmark", new UnmarkCommand(toDoList, taskStorage));
        GET_COMMAND.put("todo", new AddToDoTaskCommand(toDoList, taskStorage));
        GET_COMMAND.put("deadline", new AddDeadlineTaskCommand(toDoList, taskStorage));
        GET_COMMAND.put("event", new AddEventTaskCommand(toDoList, taskStorage));
        GET_COMMAND.put("delete", new DeleteCommand(toDoList, taskStorage));
        GET_COMMAND.put("find", new FindCommand(toDoList));
    }

    /**
     * Parses the user input to identify and execute a command.
     * Splits the input into a command name and an optional argument. If the command name is valid
     * (contained in {@link #COMMANDS}), the corresponding {@link Command} is executed with the
     * argument. If the command is invalid, an error message is printed, and the list command is
     * executed by default.
     *
     * @param input the user input string containing the command and optional argument
     * @return the executed {@link Command} object, or the list command if the input is invalid
     */
    public Command parse(String input) {
        String[] instructions = input.split(" ", 2);
        String inputCommand = instructions[0];
        String argument = instructions.length == 2 ? instructions[1] : input;
        if (COMMANDS.contains(inputCommand)) {
            GET_COMMAND.get(inputCommand).execute(argument.trim());
            return GET_COMMAND.get(inputCommand);
        }
        System.out.println("Pardon me, noble companion, but what dost thou mean to convey?\n");
        return GET_COMMAND.get("list");
    }
}
