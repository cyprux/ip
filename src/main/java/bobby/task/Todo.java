package bobby.task;

/**
 * Represents a Todo task, which has only a description and no associated dates.
 * A todo task extends Task and can be marked as done or not done.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Constructs a Todo task with the given description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone Whether the task is already marked as done.
     */
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }
}