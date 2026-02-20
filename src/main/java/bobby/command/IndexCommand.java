package bobby.command;

import bobby.exception.BobbyException;
import bobby.task.TaskList;

/**
 * Represents a command that operates on a task index.
 * <p>
 * This class centralizes index storage and index range validation,
 * so subclasses (e.g. mark/unmark/delete) can remain small and follow SLAP.
 */
public abstract class IndexCommand extends Command {
    /**
     * The 0-based index of the task this command acts on.
     */
    protected final int index;

    /**
     * Constructs an IndexCommand with the given task index.
     *
     * @param index The index of the task to operate on (0-based).
     */
    public IndexCommand(int index) {
        this.index = index;
    }

    /**
     * Validates that {@link #index} is within the valid range of the task list.
     *
     * @param tasks The task list to validate against.
     * @throws BobbyException If the index is out of range.
     */
    protected void validateIndex(TaskList tasks) throws BobbyException {
        if (index < 0 || index >= tasks.size()) {
            throw new BobbyException("That task number doesn't exist. Use 'list' to see the numbers.");
        }
    }
}