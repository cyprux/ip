import java.time.LocalDate;

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
