package Tasks;

import Common.MaybeDate;

public class DeadlineTask extends Task {
    MaybeDate deadline;

    public DeadlineTask(String description, String deadline) {
        super(description, Task.TaskType.D);
        this.deadline = MaybeDate.parse(deadline);
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
        sb.append(type).append("|").append(status).append("|").append(description).append("|").append(deadline);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskType()).append(" [").append(this.getStatusIcon()).append("] ").append(this.description).append(" (by: ").append(deadline).append(")");
        return sb.toString();
    }

}
