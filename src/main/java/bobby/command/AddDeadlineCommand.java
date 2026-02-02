package bobby.command;

import java.time.LocalDate;
import bobby.ui.Ui;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.task.Deadline;
import bobby.exception.BobbyException;
import bobby.storage.Storage;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        Task t = new Deadline(description, by);
        tasks.add(t);
        storage.save(tasks);
        ui.showTaskAdded(t, tasks.size());
    }
}
