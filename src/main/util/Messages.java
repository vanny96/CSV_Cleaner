package main.util;

public enum Messages {
    MAIN_MENU("1) Manage Headers\n2) Select File\n3) Fix CSV"),

    HEADERS_MENU("1) Show headers\n2) Add header\n3) Delete header\n4) Edit header\n5) Read from .csv file\n6) Leave"),
    SELECT_OPERATION("Select the operation (0 for menù): "),
    INVALID_VALUE("Invalid value"),
    SELECT_HEADER_NAME("Choose header name: "),
    SELECT_HEADER_TYPE("Choose header type (S=String, N=Numeric): "),
    DELETE_HEADER("Select the index of the header to delete: "),
    EDIT_HEADER("Select the index of the header to edit: "),
    INVALID_POSITION("No header found at this position"),

    GENERIC_ERROR("We don't know what happened, retry!"),
    FEW_HEADERS("Too many cell values, consider adding new headers"),

    FILE_NAME("Type name of the file (without .csv): "),

    SUCCESSFULLY_FIXED("File fixed, loot at new file in working directory");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
