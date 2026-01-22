import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
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
        ArrayList<String> tasks = new ArrayList<>();

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
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println(line);
                continue;
            }

            // echo command
            System.out.println(line);
            System.out.println(" added:" + " " + input);
            System.out.println(line);

            //  add user command to tasks
            tasks.add(input);

        }

        sc.close();
    }
}
