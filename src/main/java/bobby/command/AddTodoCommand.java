package bobby.command;

import bobby.ui.Ui;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.task.Todo;
import bobby.exception.BobbyException;
import bobby.storage.Storage;

/**
 * Represents a command that adds a Todo task into the task list.
 * A todo task contains only a description and does not have any
 * associated date. This command also updates storage and informs
 * the user interface after execution.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand with the given task description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by creating a new Todo task, adding it to
     * the task list, saving the updated list to storage, and notifying
     * the user interface.
     *
     * @param tasks The list of tasks to add the todo into.
     * @param ui The UI component responsible for displaying messages.
     * @param storage The storage component responsible for persisting tasks.
     * @throws BobbyException If saving to storage fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        Task t = new Todo(description);
        tasks.add(t);
        storage.save(tasks);
        ui.showTaskAdded(t, tasks.size());
    }
}
