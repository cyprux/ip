package bobby.command;

import bobby.task.TaskList;
import bobby.ui.Ui;
import bobby.storage.Storage;
import bobby.exception.BobbyException;

/**
 * Represents a generic command in the Bobby application.
 * All specific commands extend this class and implement the
 * execute method to define their behavior.
 */
public abstract class Command {

    /**
     * Executes the command using the given task list, UI, and storage.
     *
     * @param tasks The list of tasks managed by the application.
     * @param ui The UI component responsible for displaying output to the user.
     * @param storage The storage component responsible for saving and loading tasks.
     * @throws BobbyException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException;

    /**
     * Indicates whether this command should terminate the application.
     * Subclasses such as ExitCommand override this method to return true.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
