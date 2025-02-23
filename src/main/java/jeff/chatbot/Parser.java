package jeff.chatbot;

import java.io.IOException;
import java.util.Objects;

import jeff.task.Task;

/**
 * Parser
 */
public class Parser {
    /**
     * Returns the integer value of the String index.
     *
     * @param index String of an index value.
     * @return Integer value of String index.
     * @throws NumberFormatException If String index does not contain an integer.
     */
    public int getIndex(String index) throws NumberFormatException {
        return Integer.parseInt(index) - 1;
    }

    /**
     * Returns a boolean based on whether the parsed message array contains both the command and description.
     *
     * @param parsedMsg String array of parsed message.
     * @return Boolean of whether the parsed String array parsedMsg contains both the command and description.
     */
    private boolean checkTaskEmpty(String[] parsedMsg) {
        return parsedMsg.length <= 1;
    }

    /**
     * Saves the Tasks in TaskList to local text file.
     * @param ui Ui to get messages from.
     * @param tasks TaskList to get Tasks from.
     * @param storage Storage to save Tasks to file with.
     */
    private void saveList(Ui ui, TaskList tasks, Storage storage, Jeff jeff) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            jeff.showError(ui.getSavingError());
            e.printStackTrace();
        }
    }

    private String getTaskEmptyError(Ui ui, Jeff jeff) {
        String error = ui.getEmptyTaskError();
        assert Objects.equals(error, "Error: Task is empty.") : "Invalid error message";
        jeff.showError(error);
        return error;
    }

    /**
     * Parses the String command given and calls the corresponding methods from the Ui, TaskList and Storage objects.
     *
     * @param ui Ui object to print messages from.
     * @param tasks TaskList object to call methods from.
     * @param storage Storage object to call methods from.
     * @param command String of command to be parsed.
     */
    public String parseCommand(Ui ui, TaskList tasks, Storage storage, Jeff jeff, String command) {
        String[] parsed = command.split(" ");

        switch (parsed[0]) {
        case "list":
            return ui.getTaskList(tasks);
        case "mark":
            int index = getIndex(parsed[1]);
            Task markedTask = tasks.mark(index);
            saveList(ui, tasks, storage, jeff);
            return ui.getMarkedTask(markedTask);
        case "unmark":
            int ind = getIndex(parsed[1]);
            Task unmarkedTask = tasks.unmark(ind);
            saveList(ui, tasks, storage, jeff);
            return ui.getUnmarkedTask(unmarkedTask);
        case "todo":
            if (checkTaskEmpty(parsed)) {
                String error = ui.getEmptyTaskError();
                assert Objects.equals(error, "Error: Task is empty.") : "Invalid error message";
                jeff.showError(error);
                return error;
            }
            Task newTask = tasks.addTodo(command.split("todo ")[1]);
            saveList(ui, tasks, storage, jeff);
            return ui.getAddedTask(newTask, tasks);
        case "deadline":
            if (checkTaskEmpty(parsed)) {
                return getTaskEmptyError(ui, jeff);
            }
            Task newT = tasks.addDeadline(command.split("deadline ")[1]);
            saveList(ui, tasks, storage, jeff);
            return ui.getAddedTask(newT, tasks);
        case "event":
            if (checkTaskEmpty(parsed)) {
                return getTaskEmptyError(ui, jeff);
            }
            Task temp = tasks.addEvent(command.split("event ")[1]);
            saveList(ui, tasks, storage, jeff);
            return ui.getAddedTask(temp, tasks);
        case "delete":
            int id = getIndex(parsed[1]);
            Task deletedTask = tasks.delete(id);
            saveList(ui, tasks, storage, jeff);
            return ui.getDeletedTask(deletedTask, tasks);
        case "find":
            if (checkTaskEmpty(parsed)) {
                return getTaskEmptyError(ui, jeff);
            }
            TaskList subtasks = tasks.findTasks(command.split("find ")[1]);
            return ui.getFoundTasks(subtasks);
        case "bye":
            jeff.requestShutdown();
            return ui.getExitMsg();
        default:
            String error = ui.getInvalidCommandError();
            assert Objects.equals(error, "Error: Invalid command.") : "Invalid error message";
            jeff.showError(error);
            return error;
        }
    }
}
