package bobby.command;


import bobby.exception.BobbyException;
import bobby.storage.Storage;
import bobby.task.TaskList;
import bobby.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException;

    public boolean isExit() {
        return false;
    }
}
