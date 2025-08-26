public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected enum TaskType {
        T,
        D,
        E
    }
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getTaskType() {
        return "[" + type + "]";
    }

    protected void markAsDone() throws RedundantStateChangeException {
        if(this.isDone) {
            throw new RedundantStateChangeException("");
        }
        this.isDone = true;
    }

    protected void unmark() throws RedundantStateChangeException {
        if(!this.isDone) {
            throw new RedundantStateChangeException("");
        }
        this.isDone = false;
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskType()).append(" [").append(this.getStatusIcon()).append("] ").append(this.description);
        return sb.toString();
    }
}

