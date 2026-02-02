package bobby.task;

import java.time.LocalDate;
import bobby.util.DateTimeUtil;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " 
                + DateTimeUtil.formatDate(from) 
                + " to: "
                + DateTimeUtil.formatDate(to) + ")";
    }
}