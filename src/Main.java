import fileIO.FileReader;
import task1.Task1;
import task2.Task2;
import task3.Task3;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileReader reader = new FileReader();

        Task1 task1 = new Task1(reader.readOneLinerFileToList("input/Test1.txt"));
        System.out.println("\nTask1:");
        System.out.println(task1);

        Task2 task2 = new Task2(reader.readOneLinerFileToList("input/Test2.txt"));
        System.out.println("\nTask2:");
        task2.printResults();

        Task3 task3 = new Task3(reader.readGraphFileToSet("input/Test3.txt"));
        System.out.println("\nTask3:");
        task3.printResults();
    }
}