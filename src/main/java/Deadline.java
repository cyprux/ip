public class Deadline extends Task {
    protected String endDay;

    public Deadline(String description, String endDay) {
        super(description);
        this.endDay = endDay;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + endDay + ")";
    }
}