package Commands;

import Storage.TaskStorage;
import Tasks.DeadlineTask;
import Storage.ToDoList;

public class AddDeadlineTaskCommand implements Command {

    private ToDoList toDoList;
    private TaskStorage taskStorage;

    public AddDeadlineTaskCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }


    @Override
    public void execute(Object o) {
        String input = (String) o;
        if (input.equals("deadline")) {
            System.out.println("Thou hast overlooked the noble task of bestowing a worthy description upon this endeavor!");
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
        System.out.println("Lo! Thou art now bestowed with " + toDoList.size() + " noble quests upon thy parchment of duties.");
        System.out.println("(You now have " + toDoList.size() + " tasks in the list)\n");
    }
}

