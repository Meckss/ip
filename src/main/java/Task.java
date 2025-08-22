class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(this.getStatusIcon()).append("] ").append(this.description);
        return sb.toString();
    }
}

