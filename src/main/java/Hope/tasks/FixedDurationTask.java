package Hope.tasks;

public class FixedDurationTask extends Task {
    private String duration;

    public FixedDurationTask(String description, String duration) {
        super(description, TaskType.FD);
        this.duration = duration;
    }

    @Override
    public String format() {
        int status;

        if (this.isDone) {
            status = 1;
        } else {
            status = 0;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(type).append("|").append(status)
                .append("|").append(description)
                .append("|").append(duration);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskType()).append(" [").append(this.getStatusIcon())
                .append("] ").append(this.description).append(" (needs: ")
                .append(duration).append(")");
        return sb.toString();
    }

}
