package jeff;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Jeff {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public void run() {
        ui.run(tasks, storage);
    }

    public static void main(String[] args) {
        new Jeff("data/jeff.txt").run();
    }
}