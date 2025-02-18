package jeff;

import java.io.IOException;

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

    private void saveList(Ui ui, TaskList tasks, Storage storage) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.printSavingError();
        }
    }

    /**
     * Parses the String command given and calls the corresponding methods from the Ui, TaskList and Storage objects.
     *
     * @param ui Ui object to print messages from.
     * @param tasks TaskList object to call methods from.
     * @param storage Storage object to call methods from.
     * @param command String of command to be parsed.
     */
    public void parseCommand(Ui ui, TaskList tasks, Storage storage, String command) {
        String[] parsed = command.split(" ");

        switch (parsed[0]) {
        case "list":
            ui.printTaskList(tasks);
            break;
        case "mark":
            int index = getIndex(parsed[1]);
            Task markedTask = tasks.mark(index);
            ui.printMarkedTask(markedTask);
            saveList(ui, tasks, storage);
            break;
        case "unmark":
            int ind = getIndex(parsed[1]);
            Task unmarkedTask = tasks.unmark(ind);
            ui.printUnmarkedTask(unmarkedTask);
            saveList(ui, tasks, storage);
            break;
        case "todo":
            if (checkTaskEmpty(parsed)) {
                ui.printEmptyTaskError();
            } else {
                Task newTask = tasks.addTodo(command.split("todo ")[1]);
                ui.printAddedTask(newTask, tasks);
                saveList(ui, tasks, storage);
            }
            break;
        case "deadline":
            if (checkTaskEmpty(parsed)) {
                ui.printEmptyTaskError();
            } else {
                Task newTask = tasks.addDeadline(command.split("deadline ")[1]);
                ui.printAddedTask(newTask, tasks);
                saveList(ui, tasks, storage);
            }
            break;
        case "event":
            if (checkTaskEmpty(parsed)) {
                ui.printEmptyTaskError();
            } else {
                Task newTask = tasks.addEvent(command.split("event ")[1]);
                ui.printAddedTask(newTask, tasks);
                saveList(ui, tasks, storage);
            }
            break;
        case "delete":
            int id = getIndex(parsed[1]);
            Task deletedTask = tasks.delete(id);
            ui.printDeletedTask(deletedTask, tasks);
            saveList(ui, tasks, storage);
            break;
        default:
            ui.printInvalidCommandError();
        }
    }
}
