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
    private final String taskFilepath;
    private final String noteFilepath;

    /**
     * Constructs a Storage object and assigns the value of filepath.
     *
     * @param taskFilepath String of filepath to read and write to.
     */
    public Storage(String taskFilepath, String noteFilepath) {
        this.taskFilepath = taskFilepath;
        this.noteFilepath = noteFilepath;
    }

    /**
     * Returns string of entire file read from filepath.
     *
     * @return File from filepath as string.
     * @throws IOException If unable to read from filepath.
     */
    public String loadTasks() throws IOException {
        return new String(Files.readAllBytes(Paths.get(taskFilepath)));
    }

    public String loadNotes() throws IOException {
        return new String(Files.readAllBytes(Paths.get(noteFilepath)));
    }

    /**
     * Saves the tasks in TaskLists object by writing to filepath.
     *
     * @param tasks TaskLists with tasks to be saved.
     * @throws IOException If unable to write to filepath.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        File f = new File(taskFilepath);

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

    /**
     * Saves the Notes in NoteList object by writing to filepath.
     * @param notes NoteList with Notes to be saved.
     * @throws IOException If unable to write filepath.
     */
    public void saveNotes(NoteList notes) throws IOException {
        File f = new File(noteFilepath);

        File parentDir = f.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean dirCreated = parentDir.mkdirs();
            if (!dirCreated) {
                throw new IOException("Failed to create directories");
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (int i = 0; i < notes.getLength(); i++) {
                writer.write(notes.get(i).toString() + "\n");
            }
        }
    }
}
