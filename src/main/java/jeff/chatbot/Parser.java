package jeff.chatbot;

import java.io.IOException;
import java.util.Objects;

import jeff.notes.Note;
import jeff.task.Task;

/**
 * Parser
 */
public class Parser {
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
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
    private void saveTasks(Ui ui, TaskList tasks, Storage storage, Jeff jeff) {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            jeff.showError(ui.getSavingError());
            e.printStackTrace();
        }
    }

    private void saveNotes(Ui ui, NoteList notes, Storage storage, Jeff jeff) {
        try {
            storage.saveNotes(notes);
        } catch (IOException e) {
            jeff.showError(ui.getSavingError());
            e.printStackTrace();
        }
    }

    /**
     * Returns the empty string error after sending request to show the error in GUI.
     * @param ui Ui object to get error message from.
     * @param jeff Jeff object to send error request to.
     * @return String of error message.
     */
    private String showTaskEmptyError(Ui ui, Jeff jeff) {
        String error = ui.getEmptyTaskError();
        jeff.showError(error);
        return error;
    }

    /**
     * Returns message after adding a new Task to the list based on its type.
     * @param ui Ui object to get messages from.
     * @param tasks TaskList object to add Task to.
     * @param storage Storage object to save the tasks to.
     * @param jeff Jeff object to send message to.
     * @param command String containing task to be added.
     * @param type Enum for type of Task to be added.
     * @return String of message for adding task.
     */
    private String addTask(Ui ui, TaskList tasks, Storage storage, Jeff jeff, String command, TaskType type) {
        Task newTask = switch (type) {
        case TODO -> tasks.addTodo(command.split("todo ")[1]);
        case DEADLINE -> tasks.addDeadline(command.split("deadline ")[1]);
        case EVENT -> tasks.addEvent(command.split("event ")[1]);
        };
        saveTasks(ui, tasks, storage, jeff);
        return ui.getAddedTask(newTask, tasks);
    }

    /**
     * Parses the String command given and calls the corresponding methods from the Ui, TaskList and Storage objects.
     *
     * @param ui Ui object to print messages from.
     * @param tasks TaskList object to call methods from.
     * @param storage Storage object to call methods from.
     * @param command String of command to be parsed.
     */
    public String parseCommand(Ui ui, TaskList tasks, NoteList notes, Storage storage, Jeff jeff, String command) {
        String[] parsed = command.split(" ");

        switch (parsed[0]) {
        case "list":
            return ui.getTaskList(tasks);
        case "mark":
            int index = getIndex(parsed[1]);
            Task markedTask = tasks.mark(index);
            saveTasks(ui, tasks, storage, jeff);
            return ui.getMarkedTask(markedTask);
        case "unmark":
            int ind = getIndex(parsed[1]);
            Task unmarkedTask = tasks.unmark(ind);
            saveTasks(ui, tasks, storage, jeff);
            return ui.getUnmarkedTask(unmarkedTask);
        case "todo":
            if (checkTaskEmpty(parsed)) {
                return showTaskEmptyError(ui, jeff);
            }
            return addTask(ui, tasks, storage, jeff, command, TaskType.TODO);
        case "deadline":
            if (checkTaskEmpty(parsed)) {
                return showTaskEmptyError(ui, jeff);
            }
            return addTask(ui, tasks, storage, jeff, command, TaskType.DEADLINE);
        case "event":
            if (checkTaskEmpty(parsed)) {
                return showTaskEmptyError(ui, jeff);
            }
            return addTask(ui, tasks, storage, jeff, command, TaskType.EVENT);
        case "delete":
            int id = getIndex(parsed[1]);
            Task deletedTask = tasks.delete(id);
            saveTasks(ui, tasks, storage, jeff);
            return ui.getDeletedTask(deletedTask, tasks);
        case "find":
            if (checkTaskEmpty(parsed)) {
                return showTaskEmptyError(ui, jeff);
            }
            TaskList subtasks = tasks.findTasks(command.split("find ")[1]);
            return ui.getFoundTasks(subtasks);
        case "note":
            if (checkTaskEmpty(parsed)) {
                return showTaskEmptyError(ui, jeff);
            }
            Note newNote = notes.addNote(command.split("note ")[1]);
            saveNotes(ui, notes, storage, jeff);
            return ui.getAddedNote(newNote);
        case "notes":
            return ui.getNoteList(notes);
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
