package jeff;

import java.util.Scanner;

public class Ui {

    public String formatMessage(String message) {
        String messageFormat = "____________________________________________________________\n" +
            " %s\n" +
            "____________________________________________________________\n";
        return String.format(messageFormat, message);
    }

    public void run(TaskList tasks, Storage storage) {
        Parser parser = new Parser();

        printGreetings();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            parser.parseCommand(this, tasks, storage, input);
            input = sc.nextLine();
        }

        printExitMsg();
    }

    public void printGreetings() {
        System.out.print(formatMessage(
                " Hello! My name Jeff.\n" +
                "  What can I do for you?"
        ));
    }

    public void printExitMsg() {
        System.out.print(formatMessage(" Bye. Hope to see you again soon!"));
    }

    public void printTaskList(TaskList tasks) {
        System.out.print(formatMessage(
                " Here are the tasks in your list:\n" +
                tasks
        ));
    }

    public void printMarkedTask(Task task) {
        System.out.print(formatMessage(
                " Nice! I've marked this task as done:\n" +
                "  " + task
        ));
    }

    public void printUnmarkedTask(Task task) {
        System.out.print(formatMessage(
                " OK, I've marked this task as not done yet:\n" +
                "  " + task
        ));
    }

    public String getTasksLengthMsg(TaskList tasks) {
        return " Now you have " + tasks.getLength() + " tasks in the list.";
    }

    public void printAddedTask(Task task, TaskList tasks) {
        System.out.print(formatMessage(
                " Got it. I've added this task:\n" +
                "  " + task + "\n" +
                getTasksLengthMsg(tasks)
        ));
    }

    public void printDeletedTask(Task task, TaskList tasks) {
        System.out.print(formatMessage(
                " Noted. I've removed this task:\n" +
                "  " + task + "\n" +
                getTasksLengthMsg(tasks)
        ));
    }

    public void printFoundTasks(TaskList tasks) {
        if (tasks.checkIsEmpty()) {
            System.out.print(formatMessage(
                    " There are no matching tasks in your list."
            ));
        } else {
            System.out.print(formatMessage(
                    " Here are the matching tasks in your list:\n" +
                            tasks
            ));
        }
    }

    public void printInvalidCommandError() {
        System.out.print(formatMessage(" Error: Invalid command."));
    }

    public void printEmptyTaskError() {
        System.out.print(formatMessage(" Error: Task is empty."));
    }

    public void printLoadingError() {
        System.out.print(formatMessage(" Error: Could not load tasks locally."));
    }
    public void printSavingError() {
        System.out.print(formatMessage(" Error: Could not save tasks locally."));
    }
}
