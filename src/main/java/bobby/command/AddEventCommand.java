package bobby.command;

import java.time.LocalDate;

import bobby.exception.BobbyException;
import bobby.storage.Storage;
import bobby.task.Event;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Represents a command that adds an Event task into the task list.
 * An event has a description and occurs within a specified date range.
 * This command also ensures the updated task list is saved and the user
 * interface is notified after execution.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an AddEventCommand with the given description,
     * start date, and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public AddEventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by creating a new Event task, adding it to
     * the task list, saving the updated list to storage, and informing
     * the user interface.
     *
     * @param tasks The list of tasks to add the event into.
     * @param ui The UI component responsible for displaying messages.
     * @param storage The storage component responsible for persisting tasks.
     * @throws BobbyException If saving to storage fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        Task t = new Event(description, from, to);
        tasks.add(t);
        storage.save(tasks);
        ui.showTaskAdded(t, tasks.size());
    }
}
