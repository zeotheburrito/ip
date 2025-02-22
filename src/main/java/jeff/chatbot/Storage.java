package jeff.chatbot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Storage
 */
public class Storage {
    private final String filepath;

    /**
     * Constructs a Storage object and assigns the value of filepath.
     *
     * @param filepath String of filepath to read and write to.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Returns string of entire file read from filepath.
     *
     * @return File from filepath as string.
     * @throws IOException If unable to read from filepath.
     */
    public String load() throws IOException {
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }

    /**
     * Saves the tasks in TaskLists object by writing to filepath.
     *
     * @param tasks TaskLists with tasks to be saved.
     * @throws IOException If unable to write to filepath.
     */
    public void save(TaskList tasks) throws IOException {
        File f = new File(filepath);

        File parentDir = f.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean dirCreated = parentDir.mkdirs();
            if (!dirCreated) {
                throw new IOException("Failed to create directories");
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (int i = 0; i < tasks.getLength(); i++) {
                writer.write(tasks.get(i).toString() + "\n");
            }
        }
    }
}
