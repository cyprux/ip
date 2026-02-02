package bobby.command;

import bobby.ui.Ui;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.exception.BobbyException;
import bobby.storage.Storage;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ensureIndexInRange(index, tasks.size());
        Task removed = tasks.delete(index);
        storage.save(tasks);
        ui.showTaskDeleted(removed, tasks.size());
    }

    private static void ensureIndexInRange(int index, int size) throws BobbyException {
        if (index < 0 || index >= size) {
            throw new BobbyException("That task number doesn't exist. Use 'list' to see the numbers.");
        }
    }
}
