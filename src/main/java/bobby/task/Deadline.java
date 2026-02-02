package bobby.task;

import java.time.LocalDate;
import bobby.util.DateTimeUtil;

/**
 * Represents a Deadline task that must be completed by a specific date.
 * A deadline task extends Task by adding a due date.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the given description, due date,
     * and completion status.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task.
     * @param isDone Whether the task is already marked as done.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by;
    }

    /**
     * Returns the due date of the deadline task.
     *
     * @return The due date as a LocalDate.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the string representation of the deadline task,
     * including its due date.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + DateTimeUtil.formatDate(by) + ")";
    }
}
