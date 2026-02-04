package bobby.command;

import bobby.storage.Storage;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Represents a command that terminates the Bobby application.
 * When executed, it displays a goodbye message and signals that
 * the program should exit.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message
     * through the user interface. No changes are made to tasks or storage.
     *
     * @param tasks The list of tasks (unused in this command).
     * @param ui The UI component responsible for displaying messages.
     * @param storage The storage component (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command should terminate the application.
     *
     * @return true, since this command signals program exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
