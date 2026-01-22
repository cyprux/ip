import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
    private static int parseTaskNumber(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
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

            if (input.startsWith("mark ")) {
                int index = parseTaskNumber(input.substring(5)) - 1;

                if (index >= 0 && index < tasks.size()) {
                    Task t = tasks.get(index);
                    t.markAsDone();

                    System.out.println(line);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + t);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println(" Oops! That task number doesn't exist.");
                    System.out.println(line);
                }
                continue;
            }

            // echo command
            System.out.println(line);
            System.out.println(" added:" + " " + input);
            System.out.println(line);

            //  add user command to tasks
            Task userTask = new Task(input);
            tasks.add(userTask);

        }

        sc.close();
    }
}
