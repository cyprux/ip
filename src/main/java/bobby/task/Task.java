package bobby.task;

/**
 * Represents a generic task in the Bobby application.
 * A task has a description, a completion status, and a task type.
 * Specific task types such as Todo, Deadline, and Event extend this class.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a Task with the given description and type.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     * @param type The type of the task.
     */
    public Task(String description, TaskType type) {
        assert description != null : "Task description should not be null";
        assert type != null : "Task type should not be null";

        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Constructs a Task with the given description, type, and completion status.
     *
     * @param description The description of the task.
     * @param type The type of the task.
     * @param isDone Whether the task is already marked as done.
     */
    public Task(String description, TaskType type, boolean isDone) {
        assert description != null : "Task description should not be null";
        assert type != null : "Task type should not be null";
        
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done The new completion status.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     *
     * @return The TaskType of this task.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Returns the string representation of the task, including
     * its type icon, completion status, and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + getStatusIcon() + "] " + description;
    }
}
