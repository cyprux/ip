package bobby.command;
import bobby.ui.Ui;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.task.Todo;
import bobby.exception.BobbyException;
import bobby.storage.Storage;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        Task t = new Todo(description);
        tasks.add(t);
        storage.save(tasks);
        ui.showTaskAdded(t, tasks.size());
    }
}
