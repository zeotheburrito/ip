import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public String load() throws IOException {
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }

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
