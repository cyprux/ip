package bobby.command;

import bobby.exception.BobbyException;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Represents a command that deletes a task from the task list
 * based on its index. After deletion, the updated task list is
 * saved and the user interface is informed.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the given task index.
     *
     * @param index The index of the task to delete (0-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by validating the index, removing the
     * specified task from the task list, saving the updated list,
     * and notifying the user interface.
     *
     * @param tasks The list of tasks managed by the application.
     * @param ui The UI component responsible for displaying messages.
     * @param storage The storage component responsible for persisting tasks.
     * @throws BobbyException If the index is invalid or saving fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ensureIndexInRange(index, tasks.size());
        assert index >= 0 && index < tasks.size() : "Index should be valid after range check";
        
        Task removed = tasks.delete(index);
        storage.save(tasks);
        ui.showTaskDeleted(removed, tasks.size());
    }

    /**
     * Ensures that the provided index is within the valid range
     * of the task list.
     *
     * @param index The index to check.
     * @param size The current number of tasks.
     * @throws BobbyException If the index is out of range.
     */
    private static void ensureIndexInRange(int index, int size) throws BobbyException {
        if (index < 0 || index >= size) {
            throw new BobbyException("That task number doesn't exist. Use 'list' to see the numbers.");
        }
    }
}
