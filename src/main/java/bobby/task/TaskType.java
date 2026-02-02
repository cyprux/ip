package bobby.task;

/**
 * Represents the type of a task in the Bobby application.
 * Each task type has a short icon used for display and storage.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String icon;

    /**
     * Constructs a TaskType with the given icon.
     *
     * @param icon The short symbol representing the task type.
     */
    TaskType(String icon) {
        this.icon = icon;
    }

    /**
     * Returns the icon representing this task type.
     *
     * @return The icon string.
     */
    public String getIcon() {
        return icon;
    }
}