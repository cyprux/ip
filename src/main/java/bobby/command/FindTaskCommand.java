package bobby.command;

import java.util.List;

import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Finds tasks that match the given find query.
 */
public class FindTaskCommand extends Command {
    private final String query;

    /**
     * Creates a FindTaskCommand with the query to search for.
     *
     * @param query Query containing one or more keywords.
     */
    public FindTaskCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.findByQuery(query);
        ui.showFoundTasks(matches);
    }
}
