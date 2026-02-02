package bobby.command;

import bobby.ui.Ui;
import bobby.task.TaskList;
import bobby.storage.Storage;

/**
 * Represents a command that displays all tasks currently stored
 * in the task list. This command does not modify tasks or storage.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by showing the full task list through
     * the user interface.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui The UI component responsible for presenting the task list.
     * @param storage The storage component (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
