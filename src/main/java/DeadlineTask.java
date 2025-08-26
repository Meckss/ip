class DeadlineTask extends Task {
    String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description + " (by: " + deadline + ")", TaskType.D);
        this.deadline = deadline;
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

}
