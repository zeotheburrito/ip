package jeff.chatbot;

import java.io.IOException;

import jeff.gui.MainWindow;

/**
 * Jeff
 */
public class Jeff {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    private final MainWindow controller;

    /**
     * Constructs a Jeff object and assigns the filepath, as well as constructing the Ui, Storage and TaskList objects.
     *
     * @param filepath String of filepath to be assigned.
     */
    public Jeff(String filepath, MainWindow controller) {
        ui = new Ui();
        storage = new Storage(filepath);
        parser = new Parser();
        this.controller = controller;
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            //ui.printLoadingError(); // replace with way to get message and create new block
            tasks = new TaskList();
        }
    }

    /**
     * Returns a string containing the response based on the input.
     * @param input String input to be responded.
     * @return String output based on command in input.
     */
    public String getResponse(String input) {
        return parser.parseCommand(this.ui, this.tasks, this.storage, this, input);
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
