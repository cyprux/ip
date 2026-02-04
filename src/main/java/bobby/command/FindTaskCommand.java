package bobby.command;

import java.util.List;

import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Finds tasks that contain the given keyword in their description.
 */
public class FindTaskCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand with the keyword to search for.
     *
     * @param keyword Keyword used to match task descriptions.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.findByKeyword(keyword);
        ui.showFoundTasks(matches);
    }
}