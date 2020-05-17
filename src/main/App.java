package main;

import main.csvfixer.FixerManager;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        FixerManager fixer = new FixerManager();
        fixer.startFixing();
    }
}
