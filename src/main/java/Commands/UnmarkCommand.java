package Commands;

import CustomExceptions.RedundantStateChangeException;
import Storage.TaskStorage;
import Storage.ToDoList;

public class UnmarkCommand implements Command {
    private ToDoList toDoList;
    private TaskStorage taskStorage;

    public UnmarkCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }



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
