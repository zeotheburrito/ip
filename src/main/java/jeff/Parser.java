package jeff;

import java.io.IOException;

public class Parser {
    public int getIndex(String index) throws NumberFormatException {
        return Integer.parseInt(index) - 1;
    }

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
