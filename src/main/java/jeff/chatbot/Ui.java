package jeff.chatbot;

import jeff.notes.Note;
import jeff.task.Task;

/**
 * Ui
 */
public class Ui {
    /**
     * Returns the greeting message when the chatbot first runs.
     * @return String of greeting message.
     */
    public String getGreetings() {
        return "Hello! My name Jeff.\n"
                + "What can I do for you?";
    }

    /**
     * Returns the exit message when the user closes the chatbot.
     * @return String of exit message.
     */
    public String getExitMsg() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints all the tasks in the TaskList object.
     *
     * @param tasks TaskList to print tasks of.
     */
    public String getTaskList(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks;
    }

    /**
     * Prints the message to show the task being marked.
     *
     * @param task Task being marked.
     */
    public String getMarkedTask(Task task) {
        return "Nice! I've marked this task as done:\n"
                + " " + task;
    }

    /**
     * Prints the message to show the task being unmarked.
     *
     * @param task Task being unmarked.
     */
    public String getUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + " " + task;
    }

    public String getTasksLengthMsg(TaskList tasks) {
        return "Now you have " + tasks.getLength() + " tasks in the list.";
    }

    public String getAddedTask(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n"
                + " " + task + "\n"
                + getTasksLengthMsg(tasks);
    }

    public String getDeletedTask(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n"
                + " " + task + "\n"
                + getTasksLengthMsg(tasks);
    }

    public String getFoundTasks(TaskList tasks) {
        if (tasks.checkIsEmpty()) {
            return "There are no matching tasks in your list.";
        } else {
            return "Here are the matching tasks in your list:\n"
                    + tasks;
        }
    }

    /**
     * Returns message for added note.
     * @param note Note just added.
     * @return Message for added note.
     */
    public String getAddedNote(Note note) {
        return "Alright. I've added this note:\n"
                + " " + note + "\n";
    }

    /**
     * Returns all the Notes in NoteList.
     * @param notes NoteList to get Notes from.
     * @return String message with all Notes in NoteList.
     */
    public String getNoteList(NoteList notes) {
        return "Here are your notes:\n" + notes;
    }

    public String getInvalidCommandError() {
        return "Error: Invalid command.";
    }

    public String getEmptyTaskError() {
        return "Error: Task is empty.";
    }

    public String getLoadingError() {
        return "Error: Could not load tasks locally.";
    }
    public String getSavingError() {
        return "Error: Could not save tasks locally.";
    }
}
