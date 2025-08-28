package Commands;

import Storage.TaskStorage;
import Storage.ToDoList;
import Tasks.ToDoTask;

public class AddToDoTaskCommand implements Command {

    private final ToDoList toDoList;
    private final TaskStorage taskStorage;

    public AddToDoTaskCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }

    @Override
    public void execute(Object o) {
        String input = (String) o;
        if(input.isEmpty() || input.equals("todo")) {
            System.out.println("Thou hast overlooked the noble task of bestowing a worthy description upon this "
                    + "endeavor!");
            System.out.println("(Empty input detected, please try again)\n");
            return;
        }
        ToDoTask temp = new ToDoTask(input);
        toDoList.add(temp);
        taskStorage.append(temp);
        System.out.println("Behold, this quest hath been entrusted!");
        System.out.println(temp.toString() + "\n");
        System.out.println("Lo! Thou art now bestowed with " + toDoList.size()
                + " noble quests upon thy parchment of duties.");
        System.out.println("(You now have " + toDoList.size() + " tasks in the list)\n");
    }
}

