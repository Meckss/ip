package Commands;

import Storage.TaskStorage;
import Storage.ToDoList;
import Tasks.EventTask;

/**
 * A command that adds an event task to a to-do list and persists it to storage.
 * This class implements the {@link Command} interface and processes user input to create
 * an {@link EventTask} with a description, start time, and end time. It validates the input,
 * adds the task to the provided {@link ToDoList}, and appends it to the {@link TaskStorage}.
 * If the input is invalid, appropriate error messages are displayed.
 */
public class AddEventTaskCommand implements Command {

    /** The to-do list to which the event task will be added. */
    private ToDoList toDoList;

    /** The storage system for persisting the event task. */
    private TaskStorage taskStorage;

    /**
     * Constructs an {@code AddEventTaskCommand} with the specified to-do list and task storage.
     *
     * @param toDoList   the {@link ToDoList} to which the event task will be added
     * @param taskStorage the {@link TaskStorage} where the event task will be persisted
     */
    public AddEventTaskCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }

    /**
     * Executes the command to add an event task based on the provided input.
     * The input string is expected to contain a task description, start time, and end time,
     * separated by "/from" and "/to" (e.g., "description /from start /to end").
     * If the input is invalid (e.g., missing description, start, or end time), an error message
     * is printed, and no task is added. Otherwise, an {@link EventTask} is created, added to
     * the to-do list, persisted to storage, and a confirmation message is displayed along with
     * the updated task count.
     *
     * @param o the input object, expected to be a {@code String} containing the task description,
     *          start time, and end time in the format "description /from start /to end"
     */
    @Override
    public void execute(Object o) {
        String input = (String) o;
        if(input.equals("event")) {
            System.out.println("Thou hast overlooked the noble task of bestowing a worthy description upon this "
                    + "endeavor!");
            System.out.println("(Looks like you forgot to input description, from and to! Try again)\n");
            return;
        }
        String[] info = input.split("/from");
        if(info.length == 1) {
            System.out.println("Alas, fair traveler, thy input bears a flaw; kindly make haste and attempt anew!");
            System.out.println("(Incorrect input, please try again)\n");
            return;
        }
        String[] times = info[1].split("/to");
        if(times.length == 1) {
            System.out.println("Alas, fair traveler, thy input bears a flaw; kindly make haste and attempt anew!");
            System.out.println("(Incorrect input, please try again)\n");
            return;
        }
        EventTask temp = new EventTask(info[0].trim(), times[0].trim(), times[1].trim());
        toDoList.add(temp);
        taskStorage.append(temp);
        System.out.println("Behold, this quest hath been entrusted!:");
        System.out.println(temp.toString() + "\n");
        System.out.println("Lo! Thou art now bestowed with " + toDoList.size()
                + " noble quests upon thy parchment of duties.");
        System.out.println("(You now have " + toDoList.size() + " tasks in the to do list)\n");
    }
}
