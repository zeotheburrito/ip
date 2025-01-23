import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Jeff {
    public static class Task {
        private String desc;
        private boolean done;
        public Task(String desc) {
            this.desc = desc;
            this.done = false;
        }

        public void setDone() {
            this.done = true;
        }

        public void setNotDone() {
            this.done = false;
        }

        @Override
        public String toString() {
            if (this.done) {
                return "[X] " + this.desc;
            }
            return "[ ] " + this.desc;
        }
    }
    public static void main(String args[]) {
        String greetings = "____________________________________________________________\n" +
                " Hello! I'm Jeff\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String exitMsg = "Bye. Hope to see you again soon!";

        ArrayList<Task> list = new ArrayList<Task>();

        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] parsed = input.split(" ");
            String message = "";
            switch (parsed[0]) {
                case "list":
                    String items = "";
                    for (int i = 0; i < list.size(); i++) {
                        items += " " + (i + 1) + ". " + list.get(i) + "\n";
                    }
                    message = "____________________________________________________________\n" +
                            " Here are the tasks in your list:\n" +
                            items +
                            "____________________________________________________________\n";
                    break;
                case "mark":
                    int index = Integer.parseInt(parsed[1]) - 1;
                    list.get(index).setDone();
                    message = "____________________________________________________________\n" +
                            " Nice! I've marked this task as done:\n" +
                            "  " + list.get(index) + "\n" +
                            "____________________________________________________________\n";
                    break;
                case "unmark":
                    int ind = Integer.parseInt(parsed[1]) - 1;
                    list.get(ind).setNotDone();
                    message = "____________________________________________________________\n" +
                            " OK, I've marked this task as not done yet:\n" +
                            "  " + list.get(ind) + "\n" +
                            "____________________________________________________________\n";
                    break;
                default:
                    list.add(new Task(input));
                    message = "____________________________________________________________\n" +
                            " added: " + input + "\n" +
                            "____________________________________________________________\n";
            }

            System.out.println(message);
            input = sc.nextLine();
        }

        String message = "____________________________________________________________\n" +
                " " + exitMsg + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }
}
