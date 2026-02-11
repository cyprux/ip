package bobby.parser;

import java.time.LocalDate;

import bobby.command.AddDeadlineCommand;
import bobby.command.AddEventCommand;
import bobby.command.AddTodoCommand;
import bobby.command.Command;
import bobby.command.DeleteCommand;
import bobby.command.ExitCommand;
import bobby.command.FindTaskCommand;
import bobby.command.ListCommand;
import bobby.command.MarkCommand;
import bobby.command.UnmarkCommand;
import bobby.exception.BobbyException;
import bobby.util.DateTimeUtil;

/**
 * Parses raw user input strings into corresponding Command objects.
 * This class interprets the user's text commands and converts them into
 * executable command instances used by the application.
 */
public class Parser {

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";

    private static final String MESSAGE_EMPTY_COMMAND = "Please enter a command.";
    private static final String MESSAGE_INVALID_COMMAND = "I'm sorry, but that is an invalid command!";

    private static final String MESSAGE_MARK_USAGE =
            "Please specify which task to mark. Example: mark 2";
    private static final String MESSAGE_UNMARK_USAGE =
            "Please specify which task to unmark. Example: unmark 2";
    private static final String MESSAGE_DELETE_USAGE =
            "Please specify which task to delete. Example: delete 2";
    private static final String MESSAGE_INVALID_TASK_NUMBER =
            "Please give a valid task number. Example: mark 2";

    private static final String MESSAGE_TODO_EMPTY =
            "The description of a todo cannot be empty.";
    private static final String MESSAGE_FIND_USAGE =
            "Please provide a keyword to find. Example: find book";

    private static final String MESSAGE_DEADLINE_USAGE =
            "Use: deadline <description> /by <yyyy-mm-dd>";
    private static final String MESSAGE_EVENT_USAGE =
            "Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>";

    private static final String DELIMITER_BY = " /by ";
    private static final String DELIMITER_FROM = " /from ";
    private static final String DELIMITER_TO = " /to ";

    /**
     * Parses a full command string and returns the corresponding Command.
     *
     * @param fullCommand The raw input string entered by the user.
     * @return A Command object representing the user's request.
     * @throws BobbyException If the input is invalid or improperly formatted.
     */
    public static Command parse(String fullCommand) throws BobbyException {
        String input = fullCommand.trim();
        if (input.isEmpty()) {
            throw new BobbyException(MESSAGE_EMPTY_COMMAND);
        }

        String[] parts = input.split("\\s+", 2);
        String commandWord = parts[0];
        String args = (parts.length == 2) ? parts[1].trim() : "";

        switch (commandWord) {
        case COMMAND_BYE:
            return new ExitCommand();
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_MARK:
            return parseMark(args);
        case COMMAND_UNMARK:
            return parseUnmark(args);
        case COMMAND_DELETE:
            return parseDelete(args);
        case COMMAND_TODO:
            return parseTodo(args);
        case COMMAND_DEADLINE:
            return parseDeadline(args);
        case COMMAND_EVENT:
            return parseEvent(args);
        case COMMAND_FIND:
            return parseFind(args);
        default:
            throw new BobbyException(MESSAGE_INVALID_COMMAND);
        }
    }

    private static Command parseMark(String args) throws BobbyException {
        requireNonBlank(args, MESSAGE_MARK_USAGE);
        return new MarkCommand(parseOneBasedIndex(args, MESSAGE_INVALID_TASK_NUMBER));
    }

    private static Command parseUnmark(String args) throws BobbyException {
        requireNonBlank(args, MESSAGE_UNMARK_USAGE);
        return new UnmarkCommand(parseOneBasedIndex(args, MESSAGE_INVALID_TASK_NUMBER));
    }

    private static Command parseDelete(String args) throws BobbyException {
        requireNonBlank(args, MESSAGE_DELETE_USAGE);
        return new DeleteCommand(parseOneBasedIndex(args, MESSAGE_INVALID_TASK_NUMBER));
    }

    private static Command parseTodo(String args) throws BobbyException {
        if (args.isBlank()) {
            throw new BobbyException(MESSAGE_TODO_EMPTY);
        }
        return new AddTodoCommand(args);
    }

    private static Command parseDeadline(String args) throws BobbyException {
        String[] parts = args.split(DELIMITER_BY, 2);
        if (parts.length < 2) {
            throw new BobbyException(MESSAGE_DEADLINE_USAGE);
        }

        String description = parts[0].trim();
        String byRaw = parts[1].trim();

        if (description.isEmpty() || byRaw.isEmpty()) {
            throw new BobbyException(MESSAGE_DEADLINE_USAGE);
        }

        LocalDate by = DateTimeUtil.parseDate(byRaw);
        return new AddDeadlineCommand(description, by);
    }

    private static Command parseEvent(String args) throws BobbyException {
        String[] fromSplit = args.split(DELIMITER_FROM, 2);
        if (fromSplit.length < 2) {
            throw new BobbyException(MESSAGE_EVENT_USAGE);
        }

        String description = fromSplit[0].trim();
        String timePart = fromSplit[1].trim();

        if (description.isEmpty() || timePart.isEmpty()) {
            throw new BobbyException(MESSAGE_EVENT_USAGE);
        }

        String[] toSplit = timePart.split(DELIMITER_TO, 2);
        if (toSplit.length < 2) {
            throw new BobbyException(MESSAGE_EVENT_USAGE);
        }

        String fromRaw = toSplit[0].trim();
        String toRaw = toSplit[1].trim();

        if (fromRaw.isEmpty() || toRaw.isEmpty()) {
            throw new BobbyException(MESSAGE_EVENT_USAGE);
        }

        LocalDate from = DateTimeUtil.parseDate(fromRaw);
        LocalDate to = DateTimeUtil.parseDate(toRaw);

        return new AddEventCommand(description, from, to);
    }

    private static Command parseFind(String args) throws BobbyException {
        requireNonBlank(args, MESSAGE_FIND_USAGE);
        return new FindTaskCommand(args);
    }

    private static void requireNonBlank(String value, String messageIfBlank) throws BobbyException {
        if (value == null || value.isBlank()) {
            throw new BobbyException(messageIfBlank);
        }
    }

    /**
     * Converts a user-provided 1-based task index into a 0-based index.
     *
     * @param raw The raw index string from user input.
     * @param errorMessage The error message to show if parsing fails.
     * @return The 0-based integer index.
     * @throws BobbyException If the input is not a valid integer.
     */
    private static int parseOneBasedIndex(String raw, String errorMessage) throws BobbyException {
        try {
            return Integer.parseInt(raw.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BobbyException(errorMessage);
        }
    }

}
