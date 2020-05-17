package main.csvfixer;

import main.util.HeaderObject;
import main.util.HeaderType;
import main.util.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class HeaderManager {
    private final List<HeaderObject> headers;
    private final Scanner scanner;

    public HeaderManager(List<HeaderObject> headers, Scanner scanner) {
        this.headers = headers;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.println(Messages.HEADERS_MENU.getMessage());
        int choice = 0;
        do {
            System.out.println(Messages.SELECT_OPERATION.getMessage());
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 0:
                        System.out.println(Messages.HEADERS_MENU.getMessage());
                        break;
                    case 1:
                        showHeaders();
                        break;
                    case 2:
                        addHeader();
                        break;
                    case 3:
                        deleteHeader();
                        break;
                    case 4:
                        editHeader();
                        break;
                    case 5:
                        readFromFile();
                        break;
                    case 6:
                        break;
                    default:
                        throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println(Messages.INVALID_VALUE.getMessage());
            }
        } while (choice != 6);
    }

    private void readFromFile() {
        System.out.println(Messages.FILE_NAME.getMessage());
        String fileName = scanner.nextLine();

        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(
                    System.getProperty("user.dir") + "\\" + fileName + ".csv"));
        } catch (IOException e) {
            System.out.println("Not a valid csv file found in working directory with name: " + fileName + ".csv");
            return;
        }

        try {
            String row = null;
            while ((row = reader.readLine()) != null) {
                String[] pair = row.split(",");
                headers.add(new HeaderObject(pair[0], HeaderType.fromValue(pair[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void editHeader() {
        showHeaders();
        System.out.print(Messages.EDIT_HEADER.getMessage());
        int index = -1;
        try {
            index = Integer.parseInt(scanner.nextLine()) - 1;
            headers.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_POSITION.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(Messages.INVALID_VALUE.getMessage());
        }

        System.out.print(Messages.SELECT_HEADER_NAME.getMessage());
        String name = scanner.nextLine();

        System.out.print(Messages.SELECT_HEADER_TYPE.getMessage());
        HeaderType type = HeaderType.fromValue(scanner.nextLine());

        headers.set(index, new HeaderObject(name, type));
    }

    private void deleteHeader() {
        showHeaders();
        System.out.print(Messages.DELETE_HEADER.getMessage());
        int index = -1;
        try {
            index = Integer.parseInt(scanner.nextLine()) - 1;
            headers.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Messages.INVALID_POSITION.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(Messages.INVALID_VALUE.getMessage());
        }
    }

    private void addHeader() {
        System.out.print(Messages.SELECT_HEADER_NAME.getMessage());
        String name = scanner.nextLine();

        System.out.print(Messages.SELECT_HEADER_TYPE.getMessage());
        HeaderType type = HeaderType.fromValue(scanner.nextLine());

        headers.add(new HeaderObject(name, type));
    }

    private void showHeaders() {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            HeaderObject header = headers.get(i);
            builder.append(i + 1).append(") ");

            builder.append(header.getName()).append(", ")
                    .append(header.getType().getValue());

            builder.append("\n");
        }

        System.out.println(builder.toString());
    }
}
