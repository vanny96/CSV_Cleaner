package main.cleaners;

import main.csvfixer.RowCleaner;

import java.util.stream.Stream;

public class PdfRowCleaner implements RowCleaner {
    @Override
    public String[] cleanRow(String row) {
        row = removeQuotes(row);

        if("".equals(row))
            return null;

        String[] splittedRow = row.split(" ( )+");
        splittedRow = cleanValues(splittedRow);
        return splittedRow;
    }

    private String[] cleanValues(String[] splittedRow) {
        String[] newValues = new String[splittedRow.length];
        for(int i=0; i<splittedRow.length; i++){
            newValues[i] = splittedRow[i].trim();
        }
        return newValues;
    }

    private String removeQuotes(String row) {
        return row.substring(1, row.length()-1);
    }
}
