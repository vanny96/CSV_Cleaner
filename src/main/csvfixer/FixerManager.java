package main.csvfixer;

import main.util.HeaderObject;
import main.util.Messages;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FixerManager {
    private final List<HeaderObject> headers = new ArrayList<>();
    private Scanner scanner;
    private BufferedWriter fileWriter;
    private BufferedReader fileReader;

    public void startFixing() throws IOException {
        scanner = new Scanner(System.in);

        System.out.println(Messages.MAIN_MENU.getMessage());
        int choice = -1;
        do {
            System.out.println(Messages.SELECT_OPERATION.getMessage());
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 0:
                        System.out.println(Messages.MAIN_MENU.getMessage());
                        break;
                    case 1:
                        manageHeaders();
                        System.out.println(Messages.MAIN_MENU.getMessage());
                        break;
                    case 2:
                        selectFile();
                        System.out.println(Messages.MAIN_MENU.getMessage());
                        break;
                    case 3:
                        if(fixCsv()){
                            System.out.println(Messages.SUCCESSFULLY_FIXED.getMessage());

                            if(fileReader != null)
                                fileReader.close();

                            if(fileWriter != null){
                                fileWriter.flush();
                                fileWriter.close();
                            }
                            return;
                        }
                    default:
                        throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println(Messages.INVALID_VALUE.getMessage());
            }
        } while (choice != 5);
    }

    private boolean fixCsv() throws IOException {
        CSVFixer fixer = new CSVFixer(headers, scanner, fileWriter, fileReader);
        return fixer.fix();
    }

    private void manageHeaders() {
        HeaderManager headerManager = new HeaderManager(headers, scanner);
        headerManager.execute();
    }

    private void selectFile() {
        System.out.println(Messages.FILE_NAME.getMessage());
        String fileName = scanner.nextLine();

        try {
            fileReader = Files.newBufferedReader(Paths.get(
                    System.getProperty("user.dir") + "\\" + fileName + ".csv"));
        } catch (IOException e) {
            System.out.println("Not a valid csv file found in working directory with name: " + fileName + ".csv");
            return;
        }

        try {
            fileWriter = new BufferedWriter(
                    new FileWriter(System.getProperty("user.dir") + "\\" + fileName + "_fixed.csv"));
        } catch (IOException e) {
            System.out.println(Messages.GENERIC_ERROR.getMessage());
        }
    }
}
