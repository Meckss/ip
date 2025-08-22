class DeadlineTask extends Task {
    String deadline;

    public DeadlineTask(String description, String deadline) {
        String temp = description + " (by: " + deadline + ")";
        super(temp, TaskType.D);
        this.deadline = deadline;
    }

}
