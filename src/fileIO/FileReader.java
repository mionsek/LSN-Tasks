package fileIO;

import task3.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileReader {

    // commented - tested and it works slower than using bufferedReader
//    public List<Integer> readOneLinerFileToList(String path) throws FileNotFoundException { // using scanner
//        List<Integer> allNumbers = new ArrayList<>();
//
//        File file = new File(path);
//        Scanner scanner = new Scanner(file);
//        scanner.useDelimiter("\\s+"); //delimiter set
//
//        while (scanner.hasNext())
//            allNumbers.add(Integer.valueOf(scanner.next()));
//
//        scanner.reset();
//        return allNumbers;
//    }

    public List<Integer> readOneLinerFileToList(String path) throws IOException { // using bufferedreader
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));

        return new ArrayList<>(Arrays.asList(reader.readLine().split("\\s+")))
                .stream().map(Integer::valueOf).toList();
    }

    public Set<Pair> readGraphFileToSet(String path) throws IOException {
        Set<Pair> connectionsSet = new HashSet<>();

        String line;
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        while ((line = reader.readLine()) != null) {
            if (line.matches("\\d+ \\d+")) {
                int left = Integer.parseInt(line.substring(0, line.indexOf(' ')));
                int right = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
                connectionsSet.add(new Pair(left, right));
            }
        }
        return connectionsSet;
    }

    /*
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
     */

}
