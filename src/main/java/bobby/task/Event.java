package bobby.task;

import java.time.LocalDate;

import bobby.util.DateTimeUtil;

/**
 * Represents an Event task that occurs over a time period
 * between a start date and an end date.
 * An event task extends Task by adding a date range.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an Event task with the given description and date range.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description, TaskType.EVENT);
        assert from != null : "Event start date should not be null";
        assert to != null : "Event end date should not be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with the given description, date range,
     * and completion status.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @param isDone Whether the task is already marked as done.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        assert from != null : "Event start date should not be null";
        assert to != null : "Event end date should not be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date as a LocalDate.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date as a LocalDate.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns the string representation of the event task,
     * including its date range.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: "
                + DateTimeUtil.formatDate(from)
                + " to: "
                + DateTimeUtil.formatDate(to) + ")";
    }
}
