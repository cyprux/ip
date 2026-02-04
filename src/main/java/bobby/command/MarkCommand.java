package bobby.command;

import bobby.exception.BobbyException;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ensureIndexInRange(index, tasks.size());
        Task t = tasks.get(index);
        t.markAsDone();
        storage.save(tasks);
        ui.showTaskMarked(t);
    }

    private static void ensureIndexInRange(int index, int size) throws BobbyException {
        if (index < 0 || index >= size) {
            throw new BobbyException("That task number doesn't exist. Use 'list' to see the numbers.");
        }
    }
}
