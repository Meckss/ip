public class ListCommand implements Command{
    private static ToDoList toDoList;

    public ListCommand(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public void execute(Object o) {
        if(toDoList.size() == 0) {
            System.out.println("Thou art unburdened by quests in this moment, dear knight.\n"
                    + "(There are currently no tasks in your to do list)\n");
            return;
        }
        System.out.println("Hark! Behold the noble quests that grace thy scroll of undertakings!\n");
        System.out.println(toDoList.toString());
    }
}
