package fileIO;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {
    BufferedWriter buffer;

    public void saveToFile(String path, String content) throws IOException {
        buffer = new BufferedWriter(new java.io.FileWriter(path));
        buffer.write(content);
        buffer.close();
    }
}
