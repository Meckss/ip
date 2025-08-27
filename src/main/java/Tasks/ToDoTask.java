package Tasks;

public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description, TaskType.T);
    }

    @Override
    public String format() {
        int status;

        if(this.isDone) {
            status = 1;
        } else {
            status = 0;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(type).append("|").append(status).append("|").append(description);
        return sb.toString();
    }
}
