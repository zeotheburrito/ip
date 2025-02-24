package jeff.chatbot;

import java.io.IOException;

import jeff.gui.MainWindow;

/**
 * Jeff
 */
public class Jeff {
    private final Storage storage;
    private TaskList tasks;
    private NoteList notes;
    private final Ui ui;
    private final Parser parser;
    private final MainWindow controller;

    /**
     * Constructs a Jeff object and assigns the filepath, as well as constructing the Ui, Storage and TaskList objects.
     *
     * @param taskFilepath String of filepath to be assigned.
     */
    public Jeff(String taskFilepath, String noteFilepath, MainWindow controller) {
        ui = new Ui();
        storage = new Storage(taskFilepath, noteFilepath);
        parser = new Parser();
        this.controller = controller;
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            this.showError(ui.getLoadingError());
            tasks = new TaskList();
        }
        try {
            notes = new NoteList(storage.loadNotes());
        } catch (IOException e) {
            this.showError(ui.getLoadingError());
            notes = new NoteList();
        }
    }

    /**
     * Returns a string containing the response based on the input.
     * @param input String input to be responded.
     * @return String output based on command in input.
     */
    public String getResponse(String input) {
        return parser.parseCommand(this.ui, this.tasks, this.notes, this.storage, this, input);
    }

    /**
     * Returns the greeting message.
     * @return String containing greeting message.
     */
    public String getGreeting() {
        return this.ui.getGreetings();
    }

    /**
     * Handles requests to shut down application.
     */
    public void requestShutdown() {
        controller.closeWindow();
    }

    /**
     * Shows an error to the MainWindow controller.
     * @param error String containing error message.
     */
    public void showError(String error) {
        controller.showError(error);
    }
}
