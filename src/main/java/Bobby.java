import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
    private static int parseIndexOrThrow(String s, int size) throws BobbyException {
        int num;
        try {
            num = Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            throw new BobbyException("Please give a valid task number. Example: mark 2");
        }
        int index = num - 1;
        if (index < 0 || index >= size) {
            throw new BobbyException("That task number doesn't exist. Use 'list' to see the numbers.");
        }
        return index;
    }

    private static void printError(String line, String msg) {
        System.out.println(line);
        System.out.println(" " + msg);
        System.out.println(line);
    }

    public static void main(String[] args) {
        String logo = " ____   ___  ____  ____  __   __\n"
                    + "| __ ) / _ \\| __ )| __ ) \\ \\ / /\n"
                    + "|  _ \\| | | |  _ \\|  _ \\  \\ V / \n"
                    + "| |_) | |_| | |_) | |_) |  | |  \n"
                    + "|____/ \\___/|____/|____/   |_|  \n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println(" Hello! I'm Bobby");
        System.out.println(" What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println(line);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                }

                if (input.equals("list")) {
                    System.out.println(line);
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("mark")) {
                    String[] parts = input.split("\\s+");
                    if (parts.length < 2) {
                        throw new BobbyException("Please specify which task to mark. Example: mark 2");
                    }
                    int index = parseIndexOrThrow(parts[1], tasks.size());
                    Task t = tasks.get(index);
                    t.markAsDone();

                    System.out.println(line);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + t);
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("unmark")) {
                    String[] parts = input.split("\\s+");
                    if (parts.length < 2) {
                        throw new BobbyException("Please specify which task to unmark. Example: unmark 2");
                    }
                    int index = parseIndexOrThrow(parts[1], tasks.size());
                    Task t = tasks.get(index);
                    t.markAsNotDone();

                    System.out.println(line);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + t);
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("delete")) {
                    String[] parts = input.split("\\s+");
                    if (parts.length < 2) {
                        throw new BobbyException("Please specify which task to delete. Example: delete 2");
                    }

                    int index = parseIndexOrThrow(parts[1], tasks.size());
                    Task removed = tasks.remove(index);

                    System.out.println(line);
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("todo")) {
                    if (input.equals("todo")) {
                        throw new BobbyException("The description of a todo cannot be empty.");
                    }
                    String desc = input.substring(4).trim();
                    if (desc.isEmpty()) {
                        throw new BobbyException("The description of a todo cannot be empty.");
                    }

                    Task t = new Todo(desc);
                    tasks.add(t);

                    System.out.println(line);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    continue;
                }

                
                if (input.startsWith("deadline")) {
                    if (input.equals("deadline")) {
                        throw new BobbyException("The description of a deadline cannot be empty.");
                    }
                    String rest = input.substring(8).trim();
                    String[] parts = rest.split(" /by ", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new BobbyException("Use: deadline <description> /by <by>");
                    }

                    String desc = parts[0].trim();
                    String by = parts[1].trim();

                    Task t = new Deadline(desc, by);
                    tasks.add(t);

                    System.out.println(line);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("event")) {
                    if (input.equals("event")) {
                        throw new BobbyException("The description of an event cannot be empty.");
                    }
                    String rest = input.substring(5).trim();
                    String[] fromSplit = rest.split(" /from ", 2);
                    if (fromSplit.length < 2 || fromSplit[0].trim().isEmpty()) {
                        throw new BobbyException("Use: event <description> /from <from> /to <to>");
                    }

                    String desc = fromSplit[0].trim();
                    String[] toSplit = fromSplit[1].split(" /to ", 2);
                    if (toSplit.length < 2 || toSplit[0].trim().isEmpty() || toSplit[1].trim().isEmpty()) {
                        throw new BobbyException("Use: event <description> /from <from> /to <to>");
                    }

                    String from = toSplit[0].trim();
                    String to = toSplit[1].trim();

                    Task t = new Event(desc, from, to);
                    tasks.add(t);

                    System.out.println(line);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    continue;
                }

                throw new BobbyException("I'm sorry, but I that is an invalid command!");
            
            } catch (BobbyException e) {
                printError(line, e.getMessage());
            }
        }

        sc.close();
    }
}
