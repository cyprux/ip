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
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            List<String> lines = readAllLines();
            return parseTasks(lines);
        } catch (IOException e) {
            throw new BobbyException("Could not read saved tasks from disk.");
        }
    }

    private List<String> readAllLines() throws IOException {
        return Files.readAllLines(filePath);
    }

    private ArrayList<Task> parseTasks(List<String> lines) throws BobbyException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (!line.isEmpty()) {
                tasks.add(parseLine(line));
            }
        }
        return tasks;
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
        String[] parts = splitStorageLine(line);
        String typeIcon = getTypeIcon(parts, line);
        boolean isDone = isDone(parts);
        String description = getDescription(parts);

        if (typeIcon.equals("T")) {
            return parseTodo(description, isDone);
        }

        if (typeIcon.equals("D")) {
            return parseDeadline(parts, description, isDone, line);
        }

        if (typeIcon.equals("E")) {
            return parseEvent(parts, description, isDone, line);
        }

        throw new BobbyException("Unknown task type in save file: " + typeIcon);
    }

    private String[] splitStorageLine(String line) throws BobbyException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new BobbyException("Corrupted save file line: " + line);
        }
        return parts;
    }

    private String getTypeIcon(String[] parts, String originalLine) throws BobbyException {
        String typeIcon = parts[0].trim();
        if (typeIcon.isEmpty()) {
            throw new BobbyException("Unknown task type in save file: " + originalLine);
        }
        return typeIcon;
    }

    private boolean isDone(String[] parts) {
        return parts[1].trim().equals("1");
    }

    private String getDescription(String[] parts) {
        return parts[2].trim();
    }

    private Task parseTodo(String description, boolean isDone) {
        return new Todo(description, isDone);
    }

    private Task parseDeadline(String[] parts, String description, boolean isDone, String originalLine)
            throws BobbyException {
        if (parts.length < 4) {
            throw new BobbyException("Corrupted deadline line: " + originalLine);
        }

        LocalDate by = DateTimeUtil.parseDate(parts[3].trim());
        return new Deadline(description, by, isDone);
    }

    private Task parseEvent(String[] parts, String description, boolean isDone, String originalLine)
            throws BobbyException {
        if (parts.length < 5) {
            throw new BobbyException("Corrupted event line: " + originalLine);
        }

        LocalDate from = DateTimeUtil.parseDate(parts[3].trim());
        LocalDate to = DateTimeUtil.parseDate(parts[4].trim());
        return new Event(description, from, to, isDone);
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
