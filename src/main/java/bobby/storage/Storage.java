package bobby.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bobby.exception.BobbyException;
import bobby.task.Deadline;
import bobby.task.Event;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.task.Todo;
import bobby.util.DateTimeUtil;

/**
 * Handles loading tasks from and saving tasks to persistent storage.
 * Tasks are stored in a text file using a simple structured format.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The path to the file used for saving and loading tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "Storage file path should not be null";
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws BobbyException If the file cannot be read or contains corrupted data.
     */
    public ArrayList<Task> load() throws BobbyException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (!line.isEmpty()) {
                    tasks.add(parseLine(line));
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new BobbyException("Could not read saved tasks from disk.");
        }
    }

    /**
     * Saves the given task list to the storage file.
     *
     * @param taskList The TaskList to be saved.
     * @throws BobbyException If the tasks cannot be written to disk.
     */
    public void save(TaskList taskList) throws BobbyException {
        assert taskList != null : "TaskList to save should not be null";

        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            ArrayList<String> out = new ArrayList<>();
            ArrayList<Task> tasks = taskList.asUnmodifiableList();
            for (int i = 0; i < tasks.size(); i++) {
                out.add(toStorageString(tasks.get(i)));
            }

            Files.write(filePath, out);
        } catch (IOException e) {
            throw new BobbyException("Could not save tasks to disk.");
        }
    }

    /**
     * Parses a single line from the storage file into a Task object.
     *
     * @param line The line to parse.
     * @return The corresponding Task object.
     * @throws BobbyException If the line format is invalid or corrupted.
     */
    private Task parseLine(String line) throws BobbyException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new BobbyException("Corrupted save file line: " + line);
        }

        String typeIcon = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String desc = parts[2].trim();

        if (typeIcon.equals("T")) {
            return new Todo(desc, isDone);
        }

        if (typeIcon.equals("D")) {
            if (parts.length < 4) {
                throw new BobbyException("Corrupted deadline line: " + line);
            }
            LocalDate by = DateTimeUtil.parseDate(parts[3].trim());
            return new Deadline(desc, by, isDone);
        }

        if (typeIcon.equals("E")) {
            if (parts.length < 5) {
                throw new BobbyException("Corrupted event line: " + line);
            }
            LocalDate from = DateTimeUtil.parseDate(parts[3].trim());
            LocalDate to = DateTimeUtil.parseDate(parts[4].trim());
            return new Event(desc, from, to, isDone);
        }

        throw new BobbyException("Unknown task type in save file: " + typeIcon);
    }

    /**
     * Converts a Task object into its storage string representation.
     *
     * @param t The Task to convert.
     * @return The formatted string used for storage.
     * @throws BobbyException If the task type is unknown.
     */
    private String toStorageString(Task t) throws BobbyException {
        String done = t.isDone() ? "1" : "0";
        String type = t.getType().getIcon();
        String desc = t.getDescription();

        if (t instanceof Todo) {
            return type + " | " + done + " | " + desc;
        }

        if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return type + " | " + done + " | " + desc + " | " + d.getBy();
        }

        if (t instanceof Event) {
            Event e = (Event) t;
            return type + " | " + done + " | " + desc + " | " + e.getFrom() + " | " + e.getTo();
        }

        throw new BobbyException("Cannot save unknown task type.");
    }
}
