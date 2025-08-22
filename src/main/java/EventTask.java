class EventTask extends Task{
    String from;
    String to;

    public EventTask(String description, String from, String to) {
        String temp = description + " (from: " + from + " to: " + to + ")";
        super(temp, TaskType.E);
        this.from = from;
        this.to = to;
    }


}
