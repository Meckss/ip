package Commands;

import Storage.TaskStorage;
import Storage.ToDoList;
import Tasks.DeadlineTask;

/**
 *  Adds a new {@link DeadlineTask} to storage.
 *  This class implements the {@link Command} interface and processes user input to create a {@link DeadlineTask}
 *  with a description and a deadline. It validates the input and adds the task to the provided {@link ToDoList} and
 *  updates the {@link TaskStorage}. Invalid inputs are handled within the class.
 */
public class AddDeadlineTaskCommand implements Command {

    private ToDoList toDoList;
    private TaskStorage taskStorage;

    /**
     * Creates a {@link AddDeadlineTaskCommand} with the specified to-do list and task storage
     *
     * @param toDoList the {@link ToDoList} that {@link DeadlineTask} will be added to
     * @param taskStorage the {@link TaskStorage} that will be updated
     */
    public AddDeadlineTaskCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }

    /**
     * Executes the command to add a deadline task based on the provided input.
     * The input string is expected to contain a task description and a deadline, separated by "/by".
     * If the input is invalid (e.g., missing description or deadline), an error message is printed,
     * and no task is added. Otherwise, a {@link DeadlineTask} is created, added to the to-do list,
     * storage updated, and a confirmation message printed along with the updated task count.
     *
     * @param o the input expected to be a {@code String} containing the task description
     *          and deadline in the format "description /by deadline"
     */
    @Override
    public void execute(Object o) {
        String input = (String) o;
        if (input.equals("deadline")) {
            System.out.println("Thou hast overlooked the noble task of bestowing a worthy description upon this "
                    + "endeavor!");
            System.out.println("(Looks like you forgot to input a description and a deadline! Try again)\n");
            return;
        }
        String[] info = input.split("/by");
        if (info.length == 1) {
            System.out.println("Verily, thou hast erred in thy response; endeavor once more, brave soul!");
            System.out.println("(Incorrect input, please try again)\n");
            return;
        }
        DeadlineTask temp = new DeadlineTask(info[0].trim(), info[1].trim());
        toDoList.add(temp);
        taskStorage.append(temp);
        System.out.println("Behold, this quest hath been entrusted!");
        System.out.println(temp.toString() + "\n");
        System.out.println("Lo! Thou art now bestowed with " + toDoList.size()
                + " noble quests upon thy parchment of duties.");
        System.out.println("(You now have " + toDoList.size() + " tasks in the list)\n");
    }
}

