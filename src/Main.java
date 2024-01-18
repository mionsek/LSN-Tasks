import constants.Constants;
import fileIO.FileReader;
import fileIO.FileWriter;
import task1.Task1;
import task2.Task2;
import task3.Task3;

public class Main {
    public static void main(String[] args) {
        FileReader reader = new FileReader();
        FileWriter writer = new FileWriter();

//        Task1 task1 = new Task1(reader.readOneLinerFileToList(Constants.INPUT_PATH_1));
//        System.out.println("\nTask1:");
//        task1.printResults();
//        writer.saveToFile(Constants.OUTPUT_PATH_1, task1.toString());

//        Task2 task2 = new Task2(reader.readOneLinerFileToList(Constants.INPUT_PATH_2));
//        System.out.println("\nTask2:");
//        task2.printResults();
//        writer.saveToFile(Constants.OUTPUT_PATH_2, task2.toString());

        Task3 task3 = new Task3(reader.readGraphFileToSet(Constants.INPUT_PATH_3));
        System.out.println("\nTask3:");
        task3.printResults();
        writer.saveToFile(Constants.OUTPUT_PATH_3, task3.toString());

    }
}