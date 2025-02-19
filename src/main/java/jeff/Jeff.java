package jeff;

public class Jeff {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Jeff object and assigns the filepath, as well as constructing the Ui, Storage and TaskList objects.
     *
     * @param filepath String of filepath to be assigned.
     */
    public Jeff(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the UI object.
     */
    public void run() {
        ui.run(tasks, storage);
    }

    public static void main(String[] args) {
        new Jeff("data/jeff.txt").run();
    }
}