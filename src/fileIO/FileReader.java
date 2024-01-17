package fileIO;

import task3.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

public class FileReader {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

/*
//    commented - tested and it works slower than using bufferedReader

    public List<Integer> readOneLinerFileToList(String path) throws FileNotFoundException { // using scanner
        List<Integer> allNumbers = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\s+"); //delimiter set

        while (scanner.hasNext())
            allNumbers.add(Integer.valueOf(scanner.next()));

        scanner.reset();
        return allNumbers;
    }
*/

    public List<Integer> readOneLinerFileToList(String path) { // using bufferedreader
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            LOGGER.info("Reading from file...");
            return new ArrayList<>(Arrays.asList(reader.readLine().split("\\s+")))
                    .stream().map(Integer::valueOf).toList();
        } catch (IOException e) {
            LOGGER.info("Could not load from file!");
            throw new RuntimeException(e);
        }
    }

    public Set<Pair> readGraphFileToSet(String path) {
        Set<Pair> connectionsSet = new HashSet<>();

        String line;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            LOGGER.info("Reading from file...");
            while ((line = reader.readLine()) != null) {
                if (line.matches("\\d+ \\d+")) {
                    int left = Integer.parseInt(line.substring(0, line.indexOf(' ')));
                    int right = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
                    connectionsSet.add(new Pair(left, right));
                }
            }
            return connectionsSet;
        } catch (IOException e) {
            LOGGER.info("Could not load from file!");
            throw new RuntimeException(e);
        }
    }
}
