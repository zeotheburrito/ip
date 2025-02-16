import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Jeff {
    private static ArrayList<Task> list = new ArrayList<Task>();

    private static String getList() {
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            items.append(" ").append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return "____________________________________________________________\n" +
            " Here are the tasks in your list:\n" +
            items +
            "____________________________________________________________\n";
    }

    private static String markTask(int index) {
        list.get(index).setDone();
        return "____________________________________________________________\n" +
            " Nice! I've marked this task as done:\n" +
            "  " + list.get(index) + "\n" +
            "____________________________________________________________\n";
    }

    private static String unmarkTask(int index) {
        list.get(index).setNotDone();
        return "____________________________________________________________\n" +
            " OK, I've marked this task as not done yet:\n" +
            "  " + list.get(index) + "\n" +
            "____________________________________________________________\n";
    }

    private static String addTask(String task) {
        list.add(new Task(task));
        return "____________________________________________________________\n" +
            " added: " + task + "\n" +
            "____________________________________________________________\n";
    }

    private static String getNumOfTasksMsg() {
        return " Now you have " + list.size() + " tasks in the list.\n";
    }

    private static String addTodo(String todo) {
        Todo newTodo = new Todo(todo);
        list.add(newTodo);
        return "____________________________________________________________\n" +
            " Got it. I've added this task:\n" +
            "  " + newTodo + "\n" +
            getNumOfTasksMsg() +
            "____________________________________________________________\n";
    }

    private static String addDeadline(String deadline) {
        Deadline newDeadline = new Deadline(deadline);
        list.add(newDeadline);
        return "____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                "  " + newDeadline + "\n" +
                getNumOfTasksMsg() +
                "____________________________________________________________\n";
    }

    private static String addEvent(String event) {
        if (event.isEmpty()) {
            return "Error: The task is empty.";
        }
        Event newEvent = new Event(event);
        list.add(newEvent);
        return "____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                "  " + newEvent + "\n" +
                getNumOfTasksMsg() +
                "____________________________________________________________\n";
    }

    private static boolean isTaskEmpty(String[] parsedMsg) {
        return parsedMsg.length <= 1;
    }

    private static String getEmptyError() {
        return "____________________________________________________________\n" +
            " Error: The task is empty.\n" +
            "____________________________________________________________\n";
    }

    private static String deleteTask(int index) {
        Task deletedTask = list.remove(index);
        return "____________________________________________________________\n" +
                " Noted. I've removed this task:\n" +
                "  " + deletedTask + "\n" +
                getNumOfTasksMsg() +
                "____________________________________________________________\n";
    }

    public static void main(String args[]) {
        String greetings = "____________________________________________________________\n" +
                " Hello! I'm Jeff\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String exitMsg = "Bye. Hope to see you again soon!";

        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] parsed = input.split(" ");
            String message = switch (parsed[0]) {
                case "list" -> getList();
                case "mark" -> {
                    int index = Integer.parseInt(parsed[1]) - 1;
                    yield markTask(index);
                }
                case "unmark" -> {
                    int index = Integer.parseInt(parsed[1]) - 1;
                    yield unmarkTask(index);
                }
                case "todo" -> {
                    if (isTaskEmpty(parsed)) {
                        yield getEmptyError();
                    } else {
                        yield addTodo(input.split("todo ")[1]);
                    }
                }
                case "deadline" -> {
                    if (isTaskEmpty(parsed)) {
                        yield getEmptyError();
                    } else {
                        yield addDeadline(input.split("deadline ")[1]);
                    }
                }
                case "event" ->  {
                    if (isTaskEmpty(parsed)) {
                        yield getEmptyError();
                    } else {
                        yield addEvent(input.split("event ")[1]);
                    }
                }
                case "delete" -> {
                    int index = Integer.parseInt(parsed[1]) - 1;
                    yield deleteTask(index);
                }
                default -> {
                    yield "____________________________________________________________\n" +
                    " Error: Invalid command.\n" +
                    "____________________________________________________________\n";
                }
            };

            System.out.println(message);
            input = sc.nextLine();
        }

        String message = "____________________________________________________________\n" +
                " " + exitMsg + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }
}
