class DeadlineTask extends Task {
    String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description + " (by: " + deadline + ")", TaskType.D);
        this.deadline = deadline;
    }

}
