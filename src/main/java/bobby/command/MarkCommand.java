package bobby.command;

import bobby.exception.BobbyException;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Represents a command that marks a task in the task list as completed.
 * After updating the task's status, the updated task list is saved and
 * the user interface is notified.
 */
public class MarkCommand extends IndexCommand {

    /**
     * Constructs a MarkCommand with the given task index.
     *
     * @param index The index of the task to mark as done (0-based).
     */
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * Executes the command by validating the index, marking the specified
     * task as done, saving the updated task list, and notifying the user interface.
     *
     * @param tasks The list of tasks managed by the application.
     * @param ui The UI component responsible for displaying messages.
     * @param storage The storage component responsible for persisting tasks.
     * @throws BobbyException If the index is invalid or saving fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        validateIndex(tasks);
        assert index >= 0 && index < tasks.size() : "Index should be valid after range check";

        Task t = tasks.get(index);
        t.markAsDone();
        storage.save(tasks);
        ui.showTaskMarked(t);
    }
}