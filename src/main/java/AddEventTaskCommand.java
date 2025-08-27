public class AddEventTaskCommand implements Command{

    private ToDoList toDoList;
    private TaskStorage taskStorage;

    public AddEventTaskCommand(ToDoList toDoList, TaskStorage taskStorage) {
        this.toDoList = toDoList;
        this.taskStorage = taskStorage;
    }



    @Override
    public void execute(Object o) {
        String input = (String) o;
        if(input.equals("event")) {
            System.out.println("Thou hast overlooked the noble task of bestowing a worthy description upon this endeavor!");
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
        System.out.println("Lo! Thou art now bestowed with " + toDoList.size() + " noble quests upon thy parchment of duties.");
        System.out.println("(You now have " + toDoList.size() + " tasks in the to do list)\n");
    }
}
