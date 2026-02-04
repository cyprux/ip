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

public class Parser {
    public static Command parse(String fullCommand) throws BobbyException {
        String input = fullCommand.trim();
        if (input.isEmpty()) {
            throw new BobbyException("Please enter a command.");
        }

        if (input.equals("bye")) return new ExitCommand();
        if (input.equals("list")) return new ListCommand();

        if (input.startsWith("mark")) {
            String[] parts = input.split("\\s+", 2);
            if (parts.length < 2) throw new BobbyException("Please specify which task to mark. Example: mark 2");
            return new MarkCommand(parseOneBasedIndex(parts[1]));
        }

        if (input.startsWith("unmark")) {
            String[] parts = input.split("\\s+", 2);
            if (parts.length < 2) throw new BobbyException("Please specify which task to unmark. Example: unmark 2");
            return new UnmarkCommand(parseOneBasedIndex(parts[1]));
        }

        if (input.startsWith("delete")) {
            String[] parts = input.split("\\s+", 2);
            if (parts.length < 2) throw new BobbyException("Please specify which task to delete. Example: delete 2");
            return new DeleteCommand(parseOneBasedIndex(parts[1]));
        }

        if (input.startsWith("todo")) {
            String desc = input.substring(4).trim();
            if (desc.isEmpty()) throw new BobbyException("The description of a todo cannot be empty.");
            return new AddTodoCommand(desc);
        }

        if (input.startsWith("deadline")) {
            String rest = input.substring(8).trim();
            String[] parts = rest.split(" /by ", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new BobbyException("Use: deadline <description> /by <yyyy-mm-dd>");
            }
            LocalDate by = DateTimeUtil.parseDate(parts[1].trim());
            return new AddDeadlineCommand(parts[0].trim(), by);
        }

        if (input.startsWith("event")) {
            String rest = input.substring(5).trim();
            String[] fromSplit = rest.split(" /from ", 2);
            if (fromSplit.length < 2 || fromSplit[0].trim().isEmpty()) {
                throw new BobbyException("Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
            }

            String desc = fromSplit[0].trim();
            String[] toSplit = fromSplit[1].split(" /to ", 2);
            if (toSplit.length < 2 || toSplit[0].trim().isEmpty() || toSplit[1].trim().isEmpty()) {
                throw new BobbyException("Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
            }

            LocalDate from = DateTimeUtil.parseDate(toSplit[0].trim());
            LocalDate to = DateTimeUtil.parseDate(toSplit[1].trim());
            return new AddEventCommand(desc, from, to);
        }

        if (input.startsWith("find")) {
            String keyword = input.substring(4).trim();
            if (keyword.isEmpty()) {
                throw new BobbyException("Please provide a keyword to find. Example: find book");
            }
            return new FindTaskCommand(keyword);
        }

        throw new BobbyException("I'm sorry, but that is an invalid command!");
    }

    private static int parseOneBasedIndex(String raw) throws BobbyException {
        try {
            return Integer.parseInt(raw.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new BobbyException("Please give a valid task number. Example: mark 2");
        }
    }
    
}
