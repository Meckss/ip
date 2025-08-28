package Commands;

import CustomExceptions.RedundantStateChangeException;
import Storage.TaskStorage;
import Storage.ToDoList;

/**
 * A command that unmarks a task as not done in a to-do list and updates the storage.
 * This class implements the {@link Command} interface and processes user input to unmark
 * a task identified by its index in the {@link ToDoList}. It validates the input, updates
 * the task's status to not done, and persists the changes to the {@link TaskStorage}.
 * If the input is invalid or the task is already unmarked, appropriate error messages are displayed.
 */
public class UnmarkCommand implements Command {

    /** The to-do list containing the task to be unmarked. */
    private ToDoList toDoList;

    /** The storage system for updating the persisted tasks. */
    private TaskStorage taskStorage;


    /**
     * Constructs an {@code UnmarkCommand} with the specified to-do list and task storage.
     *
     * @param toDoList    the {@link ToDoList} containing the task to be unmarked
     * @param taskStorage the {@link TaskStorage} to be updated after unmarking the task
     */
    public UnmarkCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }

    /**
     * Executes the command to unmark a task as not done based on the provided input.
     * The input is expected to be a {@code String} representing a numeric index (1-based)
     * of the task to unmark. If the input is not a valid number, exceeds the list size,
     * or is less than 1, an error message is printed, and no task is unmarked. If the task
     * is already unmarked, a {@link RedundantStateChangeException} is caught, and an error
     * message is displayed. Otherwise, the task is unmarked, the {@link TaskStorage} is
     * updated, and a confirmation message is printed along with the task details.
     *
     * @param o the input object, expected to be a {@code String} containing the 1-based index
     *          of the task to unmark
     */
    @Override
    public void execute(Object o) {
        if (o instanceof String) {
            try {
                int input = Integer.parseInt((String) o);
            } catch (NumberFormatException e) {
                System.out.println("Pray, employ the noble digits as thy guiding input!\n"
                        + "(Please use numerics as input only)\n");
                return;
            }
            int input = Integer.parseInt((String) o);
            if(input > toDoList.size()) {
                System.out.println("Thy request doth stray beyond the hallowed limits.\n"
                        + "(The number you have input is greater than the number of tasks you currently have)\n");
                return;
            }
            if(input < 1) {
                System.out.println("Doth this be a jest, good sir?\n"
                        + "(Negative numbers are not accepted as input)\n");
                return;
            }
            try {
                toDoList.get(input - 1).unmark();
            } catch (RedundantStateChangeException e) {
                System.out.println("Verily, the noble quest was unmarked from the very outset; hath thy memory "
                        + "slipped away like a fleeting shadow?");
                System.out.println("(The task was already unmarked to begin with)\n");
                return;
            }
            taskStorage.update(toDoList);
            System.out.println("Lo! The noble quest, task #" + input
                    + " , doth still beckon thy valorous attention!");
            System.out.println(toDoList.get(input - 1) + "\n");
        }
    }
}
