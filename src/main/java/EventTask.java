class EventTask extends Task{
    String from;
    String to;

    public EventTask(String description, String from, String to) {
        super(description + " (from: " + from + " to: " + to + ")", TaskType.E);
        this.from = from;
        this.to = to;
    }


}
