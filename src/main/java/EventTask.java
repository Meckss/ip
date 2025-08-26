class EventTask extends Task{
    String from;
    String to;

    public EventTask(String description, String from, String to) {
        super(description, TaskType.E);
        this.from = from;
        this.to = to;
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
        sb.append(type).append("|").append(status).append("|").append(description).append("|").append(from).append("|").append(to);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskType()).append(" [").append(this.getStatusIcon()).append("] ").append(this.description).append(" (from: ").append(from).append(" to: ").append(to).append(")");
        return sb.toString();
    }


}
