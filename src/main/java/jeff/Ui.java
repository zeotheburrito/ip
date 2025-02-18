package jeff;

import java.util.Scanner;

public class Ui {
    /**
     * Returns string of message after formatting it.
     *
     * @param message Message to be formatted.
     * @return Formatted message.
     */
    public String formatMessage(String message) {
        String messageFormat = "____________________________________________________________\n" +
            " %s\n" +
            "____________________________________________________________\n";
        return String.format(messageFormat, message);
    }

    /**
     * Calls methods from a Parser continuously to parse the inputs from the user until the end of execution.
     *
     * @param tasks TaskList to call methods from.
     * @param storage Storage to call methods from.
     */
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

    /**
     * Prints the greeting message when the chatbot first runs.
     */
    public void printGreetings() {
        System.out.print(formatMessage(
                " Hello! My name Jeff.\n" +
                "  What can I do for you?"
        ));
    }

    /**
     * Prints the exit message when the user closes the chatbot.
     */
    public void printExitMsg() {
        System.out.print(formatMessage(" Bye. Hope to see you again soon!"));
    }

    /**
     * Prints all the tasks in the TaskList object.
     *
     * @param tasks TaskList to print tasks of.
     */
    public void printTaskList(TaskList tasks) {
        System.out.print(formatMessage(
                " Here are the tasks in your list:\n" +
                tasks
        ));
    }

    /**
     * Prints the message to show the task being marked.
     *
     * @param task Task being marked.
     */
    public void printMarkedTask(Task task) {
        System.out.print(formatMessage(
                " Nice! I've marked this task as done:\n" +
                "  " + task
        ));
    }

    /**
     * Prints the message to show the task being unmarked.
     *
     * @param task Task being unmarked.
     */
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
        System.out.print(
                " Noted. I've removed this task:\n" +
                "  " + task + "\n" +
                getTasksLengthMsg(tasks)
        );
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
