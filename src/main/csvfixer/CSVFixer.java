package main.csvfixer;

import main.cleaners.PdfRowCleaner;
import main.util.HeaderObject;
import main.util.HeaderType;
import main.util.Messages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CSVFixer {
    private final List<HeaderObject> headers;
    private final Scanner scanner;
    private final BufferedWriter fileWriter;
    private final BufferedReader fileReader;
    private final RowCleaner cleaner;

    public CSVFixer(List<HeaderObject> headers1, Scanner scanner1, BufferedWriter fileWriter1, BufferedReader fileReader1) {
        headers = headers1;
        scanner = scanner1;
        fileWriter = fileWriter1;
        fileReader = fileReader1;
        cleaner = new PdfRowCleaner();
    }

    public boolean fix() throws IOException {
        if (!checkRowLength()) {
            System.out.println(Messages.FEW_HEADERS.getMessage());
            return false;
        }

        writeHeaders();
        fixCsv();

        return true;
    }

    private void writeHeaders() throws IOException {
        for(int i=0; i<headers.size(); i++){
            fileWriter.append("\"").append(headers.get(i).getName()).append("\"");
            if(i != headers.size()-1)
                fileWriter.append(',');
        }
        fileWriter.append("\n");
    }

    private void fixCsv() throws IOException {
        int counter = 0;
        String row = null;
        while ((row = fileReader.readLine()) != null) {
            String[] cleanedRow = cleaner.cleanRow(row);
            if (cleanedRow == null)
                continue;

            for (int i = 0; i < cleanedRow.length; i++) {
                String value = cleanedRow[i];
                HeaderType type = headers.get(i).getType();

                if (type == HeaderType.STRING) {
                    fileWriter.append("\"").append(value).append("\"");
                } else {
                    fileWriter.append(value);
                }

                if (i != cleanedRow.length - 1)
                    fileWriter.append(',');
            }

            fileWriter.newLine();
            counter++;
        }
    }

    private boolean checkRowLength() throws IOException {
        fileReader.mark(500);
        String firstRow = fileReader.readLine();
        String[] cleanedValues = cleaner.cleanRow(firstRow);

        fileReader.reset();
        return cleanedValues.length <= headers.size();
    }
}
