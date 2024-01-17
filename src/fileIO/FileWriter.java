package fileIO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class FileWriter {
    private static final Logger LOGGER = Logger.getLogger( FileWriter.class.getName() );
    public void saveToFile(String path, String content) {
        try(BufferedWriter buffer = new BufferedWriter(new java.io.FileWriter(path))) {
            buffer.write(content);
            // closing is redundant
            LOGGER.info("Successfully saved to file path: " + path );
        } catch (IOException e) {
            LOGGER.warning("Could not save to file!");
//            throw new RuntimeException(e);
        }
    }
}
