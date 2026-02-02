package bobby.task;

import java.time.LocalDate;
import bobby.util.DateTimeUtil;   

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (by: " + DateTimeUtil.formatDate(by) + ")";
    }
}