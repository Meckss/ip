import java.util.ArrayList;

public class ToDoList {

    private ArrayList<Task> list;

    public ToDoList(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task t) {
        list.add(t);
    }

    public Task get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public void remove(int t) {
        list.remove(t);
    }

    @Override
    public String toString() {
        if(list.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }
        return sb.toString();
    };
}
