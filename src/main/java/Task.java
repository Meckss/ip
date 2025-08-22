abstract class Task {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskType()).append(" [").append(this.getStatusIcon()).append("] ").append(this.description);
        return sb.toString();
    }
}

