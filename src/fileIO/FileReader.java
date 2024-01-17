package fileIO;

import task3.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {

    public List<Integer> readOneLinerFileToList(String path) throws FileNotFoundException {
        List<Integer> allNumbers = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\s+"); //delimiter set

        while (scanner.hasNext())
            allNumbers.add(Integer.valueOf(scanner.next()));

        scanner.reset();
        return allNumbers;
    }

    public Set<Pair> readGraphFileToSet(String path) throws FileNotFoundException {
        Set<Pair> connectionsSet = new HashSet<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

//        while (scanner.hasNextLine()){ // I think I can leave it without checking numbers of lines to get.
        String line = scanner.nextLine();
        if (line.matches("^\\d+$")) {
            final int linesToRead = Integer.parseInt(line);
            int currentLine = 0;
            while (currentLine++ < linesToRead) {
                line = scanner.nextLine();
                if (line.matches("\\d+ \\d+")) {
                    int left = Integer.parseInt(line.substring(0, line.indexOf(' ')));
                    int right = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
                    connectionsSet.add(new Pair(left, right));
                }
            }
        }
        scanner.reset();
        return connectionsSet;
    }


}
