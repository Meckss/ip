package Commands;

import java.util.ArrayList;

import Storage.ToDoList;


public class FindCommand implements Command{
    private ToDoList toDoList;

    public FindCommand(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public void execute(Object o) {
        ToDoList tempToDoList = new ToDoList(new ArrayList<>());
        if(o instanceof String) {
            String input = (String) o;
            for(int i = 1; i <= toDoList.size(); i++) {
                if(toDoList.get(i).getDescription().contains(input)) {
                    tempToDoList.add(toDoList.get(i));
                }
            }
            if(tempToDoList.size() == 0) {
                System.out.println("Hark! No quest aligns with thine inquiry, a barren landscape of endeavor doth meet thy gaze.");
                return;
            }
            System.out.println("Hark, these are the fruits of thy inquiry.");
            System.out.printf(tempToDoList.toString());
        }

    }
}
