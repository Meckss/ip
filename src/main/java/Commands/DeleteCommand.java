package Commands;

import Storage.TaskStorage;
import Storage.ToDoList;

public class DeleteCommand implements Command {
    private ToDoList toDoList;
    private TaskStorage taskStorage;

    public DeleteCommand(ToDoList toDoList, TaskStorage taskStorage) {
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
            if (input > toDoList.size()) {
                System.out.println("Thy request doth stray beyond the hallowed limits.\n"
                        + "(The number you have input is greater than the number of tasks you currently have)\n");
                return;
            }
            if (input < 1) {
                System.out.println("Doth this be a jest, good sir?\n"
                        + "(0 and negative numbers are not accepted as input)\n");
                return;
            }

            System.out.println("Heed this decree! This noble quest hath been cast aside.");
            System.out.println(toDoList.get(input - 1).toString());
            toDoList.remove(input - 1);
            taskStorage.update(toDoList);
            System.out.println("Lo! Thou art now bestowed with " + toDoList.size() + " noble quests upon thy parchment of duties.");
            System.out.println("(You now have " + toDoList.size() + " tasks in the to do list)\n");
        }
    }
}
