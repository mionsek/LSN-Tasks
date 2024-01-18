package helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestsGenerator {

    public TestsGenerator(String path) {
        Random rand = new Random();

        int min = -100_000;
        int max = +100_000;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            for (int i = 0; i < 500_000; i++) {
                int randomNum = rand.nextInt((max - min) + 1) + min;
//            int randomNum2 = rand.nextInt((max - min) + 1) + min;
                writer
                        .append(String.valueOf(randomNum))
                        .append(" ")
//                    .append(String.valueOf(randomNum2))
//                    .append("\n")
                ;
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
