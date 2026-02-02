package bobby.command;

import java.time.LocalDate;
import bobby.ui.Ui;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.task.Deadline;
import bobby.exception.BobbyException;
import bobby.storage.Storage;

/**
 * Represents a command that adds a Deadline task into the task list.
 * This command encapsulates the task description and its due date,
 * and handles updating storage and the user interface after execution.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    /**
     * Constructs an AddDeadlineCommand with the given task description
     * and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by creating a new Deadline task,
     * adding it to the task list, saving the updated list to storage,
     * and notifying the user interface.
     *
     * @param tasks The list of tasks to add the deadline into.
     * @param ui The UI component responsible for displaying messages.
     * @param storage The storage component responsible for persisting tasks.
     * @throws BobbyException If saving to storage fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        Task t = new Deadline(description, by);
        tasks.add(t);
        storage.save(tasks);
        ui.showTaskAdded(t, tasks.size());
    }
}
