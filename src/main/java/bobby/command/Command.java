package bobby.command;

import bobby.task.TaskList;
import bobby.ui.Ui;
import bobby.storage.Storage;
import bobby.exception.BobbyException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException;

    public boolean isExit() {
        return false;
    }
}
